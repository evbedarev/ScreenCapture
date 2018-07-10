package logic.kill_monster;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Attack implements AttackInterface {
    List<RgbParameter> rgbParameterList = new ArrayList<>();
    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindPixels findImageHard;
    Logger logger;

    public Attack() throws AWTException {
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
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

        logger.debug("Finding monster " + this.toString());
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
//                mouse.mouseClick(x, y+10);
                logger.info("Killing monster " + this.toString() + ", coordinates: x=" + x + " y=" + y);
                Thread.sleep(100);
                return true;
            }
        }
        return false;
    }
}
