package logic.attacks;

import actions.SleepTime;
import find_image.FindPixels;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import logic.RgbParameter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Attack implements AttackInterface {
    List<RgbParameter> rgbParameterList = new ArrayList<>();
    Capture capture;
    final Mouse mouse;
    final FindPixels findImageHard;

    public Attack() throws AWTException {
        capture = Capture.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
    }

    @Override
    public boolean kill() throws AWTException, InterruptedException {
        for (int i=0; i < 5; i++) {
            BufferedImage screenShot = capture.takeScreenShot();
            if (findAndKill(screenShot)) return true;
        }
        return false;
    }

    @Override
    public boolean findAndKill(BufferedImage screenShot) throws
            AWTException,
            InterruptedException{

        LoggerSingle.logDebug(this.toString(), "Finding monster ");
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
                mouse.mouseClick(x, y+40);
                LoggerSingle.logInfo(this.toString(), "Attacking monster, coordinates: x=" + x + " y=" + y);
                SleepTime.sleep(100);
                return true;
            }
        }
        return false;
    }
}
