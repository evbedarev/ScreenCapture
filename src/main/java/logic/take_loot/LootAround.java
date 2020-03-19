package logic.take_loot;

import actions.SleepTime;
import checks.check_hp.CheckHP;
import find_image.FindPixels;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.screen_shot.Capture;
import logic.RgbParameter;
import logic.hands_rgb.Hands;
import main.Prop;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public class LootAround implements TakeLoot {
    private static LootAround instance;
    CheckHP checkHP = CheckHP.instance();
    Capture capture;
    Mouse mouse;
    FindPixels findImageHard;
    List<RgbParameter> hands;
    double smallAngle = Math.PI/3;
    double largeAnge = Math.PI/5;
    private LootAround() {
    }

    public static LootAround getInstance() {
        if (instance == null) {
            instance = new LootAround();
        }
        return instance;
    }

    public void initialize(Hands hands) throws AWTException {
        capture = Capture.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
        this.hands = hands.getRgbParameterList();
    }


    public void takeLootAround() throws Exception {
        if (Prop.NEED_AROUND_LOOT_SEARCH) {
            moveMouseAround(Prop.FIND_LOOT_SMALL_RADIUS, smallAngle);
//            moveMouseAround(Prop.FIND_LOOT_LARGE_RADIUS, largeAnge);
        }
    }

    private void moveMouseAround(double radius, double angle) throws Exception {
        double increment = angle;
        while (angle < 3 * Math.PI) {
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            int xCoord = 800 + (int) Math.round(x);
            int yCoord = 460 + (int) Math.round(y);

            mouse.mouseMove(xCoord,
                    yCoord);

            angle += increment;
            SleepTime.sleep(200);
            checkHand(xCoord, yCoord);
            checkHP.checkHp();
        }
        mouse.mouseMove(807, 446);
        SleepTime.sleep(100);
        pickUp();
        checkHP.checkHp();
    }

    private void checkHand(int x, int y) throws Exception {
        BufferedImage bufferedImage;
        bufferedImage = capture.takeScreenShot();
//        LoggerSingle.logInfo();
        System.out.println(bufferedImage.getRGB(x + 1, y + 1));
        if (bufferedImage.getRGB(x + 1, y + 1) > -2000000) {
            mouse.leftClick();
            SleepTime.sleep(200);
            checkHand(x, y);
        }
    }

    @Override
    public void pickUp() throws Exception {
        while (takeLoot()) {
            SleepTime.sleep(400);
        }
    }

    @Override
    public boolean take() throws AWTException, InterruptedException {
        return takeLoot();
    }

    @Override
    public boolean takeLoot() throws
            AWTException,
            InterruptedException {

        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: hands) {
            Optional<int[]> xy = findImageHard.findPixelsArround3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {675,950, 310, 600});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.leftClick();
                LoggerSingle.logInfo(this.toString(), "Taking loot arround, coordinates: x=" + x + " y=" + y);
                SleepTime.sleep(300);
                return true;
            }
        }
        return false;
    }

    @Override
    public void pickUp(BufferedImage screenShot) throws Exception {
        while (takeLoot(screenShot)) {
            SleepTime.sleep(1000);
            checkHP.checkHp();
        }
    }


    @Override
    public boolean takeLoot(BufferedImage screenShot) throws Exception {
        return false;
    }
}