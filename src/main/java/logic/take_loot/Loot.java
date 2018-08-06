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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Loot implements TakeLoot {
    List<RgbParameter> rgbParameterList = new ArrayList<>();
    Capture capture;
    final Mouse mouse;
    final Keys keys = Keys.getInstance();
    final FindPixels findImageHard;
    CheckHP checkHP = CheckHP.instance();
    LootAround lootAround = LootAround.getInstance();

    public Loot() throws AWTException {
        capture = Capture.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
    }

    @Override
    public boolean take() throws AWTException, InterruptedException {
        return takeLoot();
    }

    @Override
    public boolean takeLoot() throws
            AWTException,
            InterruptedException {

        LoggerSingle.logDebug(this.toString(), "Finding loot ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixels3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.mouseClick(x, y);
                LoggerSingle.logInfo(this.toString(),"Taking loot, coordinates: x="  + x + " y=" + y);
                SleepTime.sleep(100);
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
            lootAround.takeLootAround();
        }
    }
}
