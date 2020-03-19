package routes;

import actions.SleepTime;
import find_image.FindPixels;
import key_and_mouse.Mouse;
import logic.screen_shot.Capture;
import logic.screen_shot.ScreenShotStack;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteFromAldebaran {
    List<Route> routes = new ArrayList<>();
    FindPixels findImageHard;
    Mouse mouse;
    Capture capture;

    public RouteFromAldebaran() throws AWTException {
        findImageHard = new FindPixels();
        mouse = Mouse.getInstance();
        capture = Capture.instance();
//        routes.add(new Route(new int[] {55,703}, 3000, new int[] {1521,106}));
//        //Aldebaran
        routes.add(new Route(new int[] {55,703}, new int[] {1516,121}));
        routes.add(new Route(new int[] {430,43}, new int[] {1512,116}));
        routes.add(new Route(new int[] {274,86}, new int[] {1506,111}));
        routes.add(new Route(new int[] {761,69}, new int[] {1506,106}));
        routes.add(new Route(new int[] {761,69}, new int[] {1505,101}));
        routes.add(new Route(new int[] {1156,62}, new int[] {1509,96}));
        routes.add(new Route(new int[] {1198,66}, new int[] {1514,91}));
        routes.add(new Route(new int[] {964,60}, new int[] {1516,85}));
        routes.add(new Route(new int[] {804,62},  new int[] {1516,80}));
        routes.add(new Route(new int[] {806,74},  new int[] {1516,76}));
        routes.add(new Route(new int[] {801,70},  new int[] {1516,71}));
        routes.add(new Route(new int[] {1331,95},  new int[] {1522,65}));
        routes.add(new Route(new int[] {799,81},  new int[] {1522,61}));
        routes.add(new Route(new int[] {837,102},  new int[] {1525,162}));
//        routes.add(new Route(new int[] {768,139}, 5000, new int[] {1522,137}));

    }

    class Route {
        int[] coordsToMove;
        int[] checkCoords;

        public Route(int[] coordsToMove, int[] checkCoords) {
            this.coordsToMove = coordsToMove;
            this.checkCoords = checkCoords;
        }

        public int[] getCoordsToMove() {
            return coordsToMove;
        }

        public int[] getCheckCoords() {
            return checkCoords;
        }
    }

    public boolean checkLocation(BufferedImage screenShot, Route route) throws
            InterruptedException {

        int[] checkCoords = route.getCheckCoords();
//        logger.debug("checking location " + this.toString());
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

//            logger.info("Location true " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            SleepTime.sleep(200);
            return (x == checkCoords[0] && y == checkCoords[1]);
        }
        return false;
    }

    public void moveToLocation() throws Exception {
        System.out.println("move from aldebaran");
        for (Route route: routes) {
            int count = 0;
            mouse.mouseClick(route.coordsToMove[0], route.coordsToMove[1]);
//            SleepTime.sleep(route.getSleepTime());
//            BufferedImage screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
//            while (!checkLocation(screenshot, route)) {
//                screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
//                if (count > 40) break;
//                SleepTime.sleep(500);
//                System.out.println(count);
//                count++;
//            }
            SleepTime.sleep(4000);
//            if (count > 40) {
//                System.out.println("not in route!!!");
//                break;
//            }
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
//            SleepTime.sleep(route.getSleepTime());
            BufferedImage screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
            while (!checkLocation(screenshot, route)) {
                mouse.mouseClick(790, 520);
//                SleepTime.sleep(200);
                screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
//                System.out.println(count);
                count++;
            }
            SleepTime.sleep(500);
//            if (!checkLocation(screenshot, route)) {
//                System.out.println("not in route!!!");
//                break;
//            }
            System.out.println("On route");
        }
    }

}
