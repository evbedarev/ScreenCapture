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

    }

    public boolean checkLocation(BufferedImage screenShot, Route route) throws
            InterruptedException {

        int[] checkCoords = route.getCheckCoords();
        logger.debug("checking location " + this.toString());
        //It's bad, later change. Need to load in constructor.
        Optional<int[]> xy = findImageHard.findPixelsInImage(
                screenShot,
                -1,
                new int[] {2,2},
                new int[] {-1});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];

            logger.info("Location true" + this.toString() + ", coordinates: x=" + x + " y=" + y);
            Thread.sleep(100);
            return (x == checkCoords[0] && y == checkCoords[1]);
        }
        return false;
    }

    public void moveToLocation() throws Exception {
        for (Route route: routes) {
            mouse.mouseClick(route.coordsToMove[0], route.coordsToMove[1]);
            Thread.sleep(route.getSleepTime());
            BufferedImage screenshot = capture.takeScreenShot();
            if (!checkLocation(screenshot, route)) {
                System.out.println("not in route!!!");
                break;
            }
        }
    }

}
