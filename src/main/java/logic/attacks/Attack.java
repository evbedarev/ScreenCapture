package logic.attacks;

import actions.SleepTime;
import find_image.FindPixels;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.RgbParameter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Attack implements AttackInterface {
    List<RgbParameter> rgbParameterList = new ArrayList<>();
    final Mouse mouse;
    final FindPixels findImageHard;

    public Attack() throws AWTException {
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
    }

    @Override
    public boolean kill() throws AWTException, InterruptedException {
        return findAndKill();
    }

    @Override
    public boolean findAndKill() throws
            AWTException,
            InterruptedException{

        LoggerSingle.logDebug(this.toString(), "Finding attack cursor");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsNear3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                mouse.mouseClick(x, y+40);
                LoggerSingle.logInfo(this.toString(), "Attacking monster, coordinates: x=" + x + " y=" + y);
                SleepTime.sleep(1000);
                return true;
            }
        }
        return false;
    }
}
