package logic.take_loot;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class TakeLootArround implements TakeLoot {
    Capture
            capture;
    final Mouse mouse;
    final Keys keys;
    final FindPixels findImageHard;
    Logger logger;
    int mainRgb  = -1184260;
    int[] subImageSize = new int[] {100,100};
    int[] ancillaryRgb = new int[] {-1184260};
    //-1250053,
    double angle = Math.PI/4;

    public TakeLootArround(Logger logger) throws AWTException {
        this.logger = logger;
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
//            mouse.mouseMove(1000, 600);
            return true;
        }
        return false;
    }


    public void moveMouseArround() throws Exception {
        while (angle < 3 * Math.PI) {
            double radius = 55;

            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);

            mouse.mouseMove(800 + (int) Math.round(x),
                    450 + (int) Math.round(y));
            angle = angle + Math.PI/4;
            Thread.sleep(200);
            pickUp();
        }
        angle = Math.PI/3;
    }

    @Override
    public void pickUp() throws Exception {
        while (take()) {
            Thread.sleep(800);
        }
    }
}
