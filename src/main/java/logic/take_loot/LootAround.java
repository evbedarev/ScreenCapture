package logic.take_loot;

import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public class LootAround implements TakeLoot {
    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindPixels findImageHard;
    Logger logger;
    List<RgbParameter> rgbParameterList;
    double smallAngle = Math.PI/4;
    double largeAnge = Math.PI/5;

    public LootAround(Logger logger) throws AWTException {
        this.logger = logger;
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
        findImageHard = new FindPixels();

        rgbParameterList.add(new RgbParameter(-1184260,
                new int[] {100,100},
                new int[] {-1184260}));

        rgbParameterList.add(new RgbParameter(-1250309,
                new int[] {100,100},
                new int[] {-1250309}));

    }

    public void takeLootAround() throws Exception {
        if (Prop.NEED_AROUND_LOOT_SEARCH) {
            moveMouseAround(Prop.FIND_LOOT_SMALL_RADIUS, smallAngle);
            moveMouseAround(Prop.FIND_LOOT_LARGE_RADIUS, largeAnge);
        }
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


//2018-06-28 17:13:54 INFO  CompareFragmentImage:34 - Equals rgb for fragments 0, 1 ,2 is:
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -16448508 x=29 y=22
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -16316922 x=24 y=29
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -197637 x=15 y=16
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -3421213 x=26 y=27
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -3289884 x=9 y=26
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -921349 x=14 y=17
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -2171408 x=22 y=20
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -16251129 x=7 y=7
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -2171151 x=24 y=18
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -1315846 x=15 y=19
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -1052678 x=16 y=17
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -1184517 x=13 y=23
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:37 - equals RGB = -1250310 x=16 y=19
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:43 - Equals rgb for fragments 0, 3 ,4 is:
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:46 - equals RGB = -460549 x=16 y=16
//        2018-06-28 17:13:54 INFO  CompareFragmentImage:46 - equals RGB = -1250309 x=16 y=20