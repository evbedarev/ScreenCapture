package logic.take_loot;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

public class Loot implements TakeLoot {
    String rootDir = "";
    String wildcard = "fragmq*";

    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindPixels findImageHard;
    final Logger logger = Logger.getLogger(this.getClass());
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
    public boolean takeLoot() throws
            IOException,
            AWTException,
            InterruptedException {

        BufferedImage screenShot = capture.takeScreenShot();
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
            logger.info("Taking loot, coordinates: x=" + x + " y=" + y);
            return true;
        }
        return false;
    }

    @Override
    public void pickUp() throws Exception {
        TakeLoot takeLoot = this;
        while (takeLoot.takeLoot()) {
            Thread.sleep(1500);
        }
    }
}
