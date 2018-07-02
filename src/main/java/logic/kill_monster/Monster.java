package logic.kill_monster;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Monster implements KillMonster {
    List<RgbParameter> rgbParameterList = new ArrayList<>();
    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindPixels findImageHard;
    Logger logger;


    public Monster() throws AWTException {
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
        findImageHard = new FindPixels();
    }

    @Override
    public boolean kill() throws AWTException, InterruptedException {
        BufferedImage screenShot = capture.takeScreenShot();
        return findAndKill(screenShot);
    }

    /**
     * Ищет пиксели а экране и жмет при нахождении.
     * @return boolean;
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */

    @Override
    public boolean findAndKill(BufferedImage screenShot) throws
            AWTException,
            InterruptedException{

        logger.debug("Finding loot " + this.toString());
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
                logger.info("Taking loot " + this.toString() + ", coordinates: x=" + x + " y=" + y);
                Thread.sleep(100);
                return true;
            }
        }
        return false;
    }
}
