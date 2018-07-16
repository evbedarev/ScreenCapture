package logic.take_loot;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import logic.RgbParameter;
import logic.hands_rgb.Hands;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public class LootAround implements TakeLoot {
    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindPixels findImageHard;
    Logger logger;
    List<RgbParameter> hands;
    double smallAngle = Math.PI/4;
    double largeAnge = Math.PI/5;

    public LootAround(Hands hands, Logger logger) throws AWTException {
        this.logger = logger;
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
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

            mouse.mouseMove(800 + (int) Math.round(x),
                    450 + (int) Math.round(y));
            angle += increment;
            Thread.sleep(200);
            pickUp();
        }
    }

    @Override
    public void pickUp() throws Exception {
        while (take()) {
            Thread.sleep(200);
        }
    }

    @Override
    public boolean take() throws AWTException, InterruptedException {
        BufferedImage screenShot = capture.takeScreenShot();
        return takeLoot(screenShot);
    }

    @Override
    public boolean takeLoot(BufferedImage screenShot) throws
            AWTException,
            InterruptedException {

        logger.debug("Finding loot " + this.toString());
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: hands) {
            Optional<int[]> xy = findImageHard.findPixelsInImage(
                    screenShot,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.mouseClick(x, y);
                logger.info("Taking loot " + this.toString() + ", coordinates: x=" + x + " y=" + y);
                Thread.sleep(100);
                return true;
            }
        }
        return false;
    }
}