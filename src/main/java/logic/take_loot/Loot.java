package logic.take_loot;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import logic.RgbParameter;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Loot implements TakeLoot {
    List<RgbParameter> rgbParameterList = new ArrayList<>();
    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindPixels findImageHard;
    LootAround lootAround = LootAround.getInstance();

    public Loot() throws AWTException {
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
        findImageHard = new FindPixels();
    }

    @Override
    public boolean take() throws AWTException, InterruptedException {
        for (int i = 0; i < Prop.COUNT_TO_FIND_LOOT; i++) {
            BufferedImage screenShot = capture.takeScreenShot();
            if (takeLoot(screenShot))
                return true;
        }
        return false;
    }

    @Override
    public boolean takeLoot(BufferedImage screenShot) throws
            AWTException,
            InterruptedException {

        LoggerSingle.logDebug(this.toString(), "Finding loot ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsInImage(
                    screenShot,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.mouseClick(x, y);
                LoggerSingle.logInfo(this.toString(),"Taking loot, coordinates: x="  + x + " y=" + y);
                Thread.sleep(100);
                return true;
            }
        }
        return false;
    }

    @Override
    public void pickUp() throws Exception {
        while (take()) {
            Thread.sleep(1000);
            lootAround.takeLootAround();
        }
    }
}
