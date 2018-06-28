package logic.take_loot;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class LootAround implements TakeLoot {
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
    double smallAngle = Math.PI/4;
    double largeAnge = Math.PI/5;

    public LootAround(Logger logger) throws AWTException {
        this.logger = logger;
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
        findImageHard = new FindPixels();
    }

    public void takeLootAround() throws Exception {
        moveMouseAround(Prop.getFindLootSmallRadius(),smallAngle);
        moveMouseAround(Prop.getFindLootLargeRadius(),largeAnge);
    }

    private void moveMouseAround(double radius, double angle) throws Exception {
        double increment = angle;
        while (angle < 3 * Math.PI) {
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);

            mouse.mouseMove(800 + (int) Math.round(x),
                    450 + (int) Math.round(y));
            angle += increment;
            Thread.sleep(200);
            pickUp();
        }
    }

    @Override
    public void pickUp() throws Exception {
        while (take()) {
            Thread.sleep(600);
        }
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
            Thread.sleep(100);
            return true;
        }
        return false;
    }
}
