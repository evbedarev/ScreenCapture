package logic.take_loot;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Loot implements TakeLoot {
    String rootDir = "";
    String wildcard = "fragmq*";
    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindPixels findImageHard;
    Logger logger;
    int mainRgb;
    int[] subImageSize;
    int[] ancillaryRgb;

    public Loot() throws AWTException {
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
        findImageHard = new FindPixels();
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
        Optional<int[]> xy = findImageHard.findPixelsInImage(
                screenShot,
                mainRgb,
                subImageSize,
                ancillaryRgb);

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];
            mouse.mouseClick(x , y );
            logger.info("Taking loot " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            Thread.sleep(200);
            return true;
        }
        return false;
    }

    @Override
    public void pickUp() throws Exception {
        while (take()) {
            Thread.sleep(1500);
        }
    }
}
