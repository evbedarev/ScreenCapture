package logic.kill_monster;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

public class Monster implements KillMonster {
    String rootDir = "";
    String wildcard = "fragm*";

    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindPixels findImageHard;
    private final Logger logger = Logger.getLogger(this.getClass());

    int mainRgb;
    int[] subImageSize;
    int[] ancillaryRgb;


    public Monster() throws AWTException {
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
        findImageHard = new FindPixels();
    }

    /**
     *
     * @return boolean;
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */

    @Override
    public boolean findAndKill() throws
            IOException,
            AWTException,
            InterruptedException{

        BufferedImage screenShot = capture.takeScreenShot();
        logger.info("Finding monster " + this.toString());

        //It's bad, later change. Need to load in constructor.

        Optional<int[]> xy = findImageHard.findPixelsInImage(
                screenShot,
                mainRgb,
                subImageSize,
                ancillaryRgb);
        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];
            mouse.mouseClick(x + 18, y + 20);
            logger.info("Killing monster, coordinates: x=" + (x+18) + " y=" + (y + 20));
            Thread.sleep(200);
            return true;
        }

        return false;
    }
}
