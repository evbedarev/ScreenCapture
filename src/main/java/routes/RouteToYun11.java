package routes;

import find_image.FindPixels;
import key_and_mouse.Mouse;
import logic.Capture;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteToYun11 {
    List<Route> routes = new ArrayList<>();
    Logger logger;
    FindPixels findImageHard;
    Mouse mouse;
    Capture capture;

    public RouteToYun11(Logger logger) throws AWTException {
        this.logger = logger;
        findImageHard = new FindPixels();
        mouse = new Mouse();
        capture = Capture.instance();
        routes.add(new Route(new int[] {55,703}, 3000, new int[] {1521,106}));
//        //Aldebaran
//        routes.add(new Route(new int[] {55,703}, 3000, new int[] {1513,96}));
//        routes.add(new Route(new int[] {430,43}, 5000, new int[] {1509,91}));
//        routes.add(new Route(new int[] {321,37}, 5000, new int[] {1504,86}));
//        routes.add(new Route(new int[] {681,37}, 5000, new int[] {1502,81}));
//        routes.add(new Route(new int[] {840,39}, 5000, new int[] {1503,76}));
//        routes.add(new Route(new int[] {1086,38}, 5000, new int[] {1506,71}));
//        routes.add(new Route(new int[] {1162,41}, 5000, new int[] {1510,66}));
//        routes.add(new Route(new int[] {1082,49}, 5000, new int[] {1513,61}));
//        routes.add(new Route(new int[] {803,30}, 5000, new int[] {1513,55}));
//        routes.add(new Route(new int[] {803,30}, 5000, new int[] {1513,51}));
//        routes.add(new Route(new int[] {803,30}, 5000, new int[] {1513,47}));
//        routes.add(new Route(new int[] {1107,65}, 5000, new int[] {1517,39}));
//        routes.add(new Route(new int[] {1052,60}, 5000, new int[] {1520,35}));
//        routes.add(new Route(new int[] {768,139}, 5000, new int[] {1522,137}));

    }

    public boolean checkLocation(BufferedImage screenShot, Route route) throws
            InterruptedException {

        int[] checkCoords = route.getCheckCoords();
        logger.debug("checking location " + this.toString());
        //It's bad, later change. Need to load in constructor.
        Optional<int[]> xy = findImageHard.findPixelsInImage(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];

            logger.info("Location true" + this.toString() + ", coordinates: x=" + x + " y=" + y);
            Thread.sleep(200);
            return (x == checkCoords[0] && y == checkCoords[1]);
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
                Thread.sleep(1000);
                System.out.println(count);
                count++;
            }
            Thread.sleep(500);
            if (count > 40) {
                System.out.println("not in route!!!");
                break;
            }
//            if (!checkLocation(screenshot, route)) {
//                System.out.println("not in route!!!");
//                break;
//            }
            System.out.println("On route");
        }
    }

    public void moveByMap() throws Exception {
        for (Route route: routes) {
            int count = 0;
//            Thread.sleep(route.getSleepTime());
            BufferedImage screenshot = capture.takeScreenShot();
            while (!checkLocation(screenshot, route)) {
                mouse.mouseClick(790, 520);
//                Thread.sleep(200);
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

}
