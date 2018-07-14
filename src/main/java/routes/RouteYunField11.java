package routes;

import actions.Actions;
import checks.location.VerifyMap;
import checks.location.YunField12;
import find_image.FindPixels;
import key_and_mouse.Mouse;
import logic.Capture;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteYunField11 {
    List<Route> routes = new ArrayList<>();
    List<Route> routes1 = new ArrayList<>();
    Logger logger;
    FindPixels findImageHard;
    Mouse mouse;
    Capture capture;
    boolean onRoute;
    Actions actions;
    VerifyMap verifyMap;

    public RouteYunField11(Logger logger) throws AWTException {
        onRoute = true;
        this.logger = logger;
        findImageHard = new FindPixels();
        mouse = new Mouse();
        capture = Capture.instance();
        actions = Actions.instance();
//        routes.add(new Route(new int[] {55,703}, 3000, new int[] {1521,106}));
//        //Aldebaran
        routes.add(new Route(new int[] {812,76}, 3000, new int[] {1525,159}));
    }

    public boolean checkLocation(BufferedImage screenShot, Route route) throws
            InterruptedException {

        int[] checkCoords = route.getCheckCoords();
        logger.debug("checking location " + this.toString());
        //It's bad, later change. Need to load in constructor.
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {1460, 1586, 42, 168});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];

            logger.info("Location true " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            Thread.sleep(200);
            return (x == checkCoords[0] && y == checkCoords[1]);
        }
        return false;
    }

    public boolean checkLocationY(BufferedImage screenShot, Route route) throws
            InterruptedException {

        int[] checkCoords = route.getCheckCoords();
        logger.debug("checking location " + this.toString());
        //It's bad, later change. Need to load in constructor.
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {1460, 1586, 42, 168});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];

            logger.info("Location true " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            Thread.sleep(200);
            return (y == checkCoords[1]);
        }
        return false;
    }

    public boolean checkLocationX(BufferedImage screenShot, Route route) throws
            InterruptedException {

        int[] checkCoords = route.getCheckCoords();
        logger.debug("checking location " + this.toString());
        //It's bad, later change. Need to load in constructor.
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {1460, 1586, 42, 168});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];

            logger.info("Location true " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            Thread.sleep(200);
            return (x == checkCoords[0]);
        }
        return false;
    }

    public boolean checkLocationTP(BufferedImage screenShot, int[] area) throws
            InterruptedException {

        logger.debug("checking location " + this.toString());
        //It's bad, later change. Need to load in constructor.
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {area[0], area[1], area[2], area[3]});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];

            logger.info("Location true " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            Thread.sleep(200);
            return true;
        }
        return false;
    }

    public void moveToLocation() throws Exception {
        for (Route route: routes) {
            int count = 0;
            mouse.mouseClick(route.coordsToMove[0], route.coordsToMove[1]);
//            Thread.sleep(route.getSleepTime());
            BufferedImage screenshot = capture.takeScreenShot();
            while (!checkLocation(screenshot, route)) {
                screenshot = capture.takeScreenShot();
                if (count > 40) break;
                Thread.sleep(500);
                System.out.println(count);
                count++;
            }
            Thread.sleep(500);
            if (count > 40) {
                System.out.println("not in route!!!");
                break;
            }
            if (!checkLocation(screenshot, route)) {
                System.out.println("not in route!!!");
                break;
            }
            System.out.println("On route");
        }
    }

    public void moveByMap() throws Exception {
        for (Route route: routes) {
            int count = 0;
            BufferedImage screenshot = capture.takeScreenShot();
            while (!checkLocation(screenshot, route)) {
                mouse.mouseClick(route.getCoordsToMove()[0], route.getCoordsToMove()[1]);
                Thread.sleep(500);
                screenshot = capture.takeScreenShot();
//                System.out.println(count);
                count++;
            }
            Thread.sleep(500);
//            if (!checkLocation(screenshot, route)) {
//                System.out.println("not in route!!!");
//                break;
//            }
        System.out.println("On route");
    }
}

    public void tpUntil() throws Exception {
        BufferedImage screenshot = capture.takeScreenShot();
        boolean Location1 = checkLocationTP(screenshot, new int[] {1466,1474,61,76});
        while (!Location1) {
            actions.teleport();
            Thread.sleep(5000);
            screenshot = capture.takeScreenShot();
            Location1 = checkLocationTP(screenshot, new int[] {1466,1475,61,76});
        }
        System.out.println("on location");

        screenshot = capture.takeScreenShot();
        while (!checkLocationY(screenshot, new Route(new int[] {800,582}, 3000, new int[] {1525,62}))) {
            mouse.mouseClick(805, 400);
            Thread.sleep(500);
            screenshot = capture.takeScreenShot();
        }

        System.out.println("on location");

        while (!checkLocationX(screenshot, new Route(new int[] {800,582}, 3000, new int[] {1466,62}))) {
            mouse.mouseClick(726, 461);
            Thread.sleep(500);
            screenshot = capture.takeScreenShot();
        }


        verifyMap = new YunField12();
        while (!verifyMap.onDesiredLocation()) {
            mouse.mouseClick(805, 400);
            Thread.sleep(500);
        }
    }

}
