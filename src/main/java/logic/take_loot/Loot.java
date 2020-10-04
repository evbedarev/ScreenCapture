package logic.take_loot;

import actions.Actions;
import actions.SleepTime;
import checks.check_hp.CheckHP;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.LogicLocation;
import logic.RgbParameter;
import logic.screen_shot.ScreenShot;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Loot implements TakeLoot {
    List<RgbParameter> rgbParameterList = new ArrayList<>();
    final Mouse mouse;
    final Keys keys = Keys.getInstance();
    private Actions actions = Actions.instance();
    private static LogicLocation logicLocation;
    final FindPixels findImageHard;
    CheckHP checkHP = CheckHP.instance();
//    LootAround lootAround = LootAround.getInstance();

    public Loot() throws AWTException {
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
    }

    @Override
    public boolean take() throws Exception {
        return takeLoot();
    }

    @Override
    public boolean takeLoot() throws Exception {
        LoggerSingle.logDebug(this.toString(), "Finding loot ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsArround3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {0,1600,127,900});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.mouseClick(x, y);
                LoggerSingle.logInfo(this.toString(),"Taking loot, coordinates: x="  + x + " y=" + y + " mainRgb=" + parameter.getMainRgb());
                SleepTime.sleep(calculateTimeToSleep(new int[] {x, y}));
                mouse.mouseMove(0,0);
                return true;
            }
        }
        return false;
    }

    private int calculateTimeToSleep(int[] xy) {
        double x1 = xy[0];
        double y1 = xy[1];
        double x_lenght = Math.pow(Math.abs(x1 - 800),2);
        double y_lenght = Math.pow(Math.abs(y1 - 450), 2);
        int z = (int) Math.sqrt(x_lenght + y_lenght);
        return z * 3;
    }

    @Override
    public boolean takeLoot(BufferedImage screenShot) throws Exception {
        LoggerSingle.logDebug(this.toString(), "Finding loot ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                    screenShot,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {0,1600,127,900});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.mouseClick(x, y);
                LoggerSingle.logInfo(this.toString(),"Taking loot, coordinates: x="  + x + " y=" + y);
                SleepTime.sleep(calculateTimeToSleep(new int[] {x, y}));
                mouse.mouseMove(0,0);
                return true;
            }
        }
        return false;
    }

    @Override
    public void pickUp() throws Exception {
        int repeat = 0;
        while (take()) {
            SleepTime.sleep(1000);
            Prop.logic.findAndKillWithoutTakingLoot();
            if (repeat++ % 12 == 0) {
                actions.stepAside(new int[]{75, 150});
                SleepTime.sleep(1400);
            }
        }
        checkHP.checkHp();
    }

    @Override
    public void pickUp(BufferedImage screenShot) throws Exception {
        while (takeLoot(screenShot)) {
            screenShot = Prop.context.getBean(ScreenShot.class).pop();
            SleepTime.sleep(1000);
        }
        checkHP.checkHp();
    }
}
