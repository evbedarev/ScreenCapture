package logic.take_loot;

import actions.SleepTime;
import checks.check_hp.CheckHP;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import logic.RgbParameter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Loot implements TakeLoot {
    List<RgbParameter> rgbParameterList = new ArrayList<>();
    Capture capture;
    final Mouse mouse;
    static Optional<int[]> xy = Optional.empty();
    final Keys keys = Keys.getInstance();
    final FindPixels findImageHard;
    CheckHP checkHP = CheckHP.instance();
    StringBuilder message = new StringBuilder();
    LootAround lootAround = LootAround.getInstance();

    public Loot() throws AWTException {
        capture = Capture.instance();
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
        xy = Optional.empty();
        for (RgbParameter parameter: rgbParameterList) {
            xy = findImageHard.findPixelsArround3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {0,1600,127,900});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.mouseClick(x, y);
                LoggerSingle.logInfo(this.toString(),createMsg(x,y).toString());
                SleepTime.sleep(100);
                mouse.mouseMove(0,0);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean takeLoot(BufferedImage screenShot) throws Exception {
        LoggerSingle.logDebug(this.toString(), "Finding loot ");
        //It's bad, later change. Need to load in constructor.
        xy = Optional.empty();
        for (RgbParameter parameter: rgbParameterList) {
            xy = findImageHard.findPixelsInImageInArea(
                    screenShot,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {0,1600,127,900});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.mouseClick(x, y);
                LoggerSingle.logInfo(this.toString(),createMsg(x,y).toString());
                SleepTime.sleep(100);
                mouse.mouseMove(0,0);
                return true;
            }
        }
        return false;
    }

    @Override
    public void pickUp() throws Exception {
        while (take()) {
            SleepTime.sleep(1000);
            checkHP.checkHp();
        }
    }

    @Override
    public void pickUp(BufferedImage screenShot) throws Exception {
        while (takeLoot(screenShot)) {
            screenShot = capture.takeScreenShot();
            SleepTime.sleep(1000);
            checkHP.checkHp();
        }
    }

    private StringBuilder createMsg(int x, int y) {
        message.delete(0,message.length());
        message.append("Taking loot, coordinates: x=");
        message.append(x);
        message.append(" y=");
        message.append(y);
        return message;
    }
}
