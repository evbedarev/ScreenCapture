package routes;

import actions.Actions;
import actions.SleepTime;
import checks.afterDeath.AfterDeath;
import checks.location.VerifyMap;
import find_image.FindPixels;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.screen_shot.Capture;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public abstract class RouteModel implements RouteToLocation {
    AfterDeath checkDie = Prop.checkDie;
    FindPixels findImageHard;
    Mouse mouse;
    Capture capture;
    boolean onRoute;
    Actions actions;
    VerifyMap verifyMap;
    int count = 0;

    public RouteModel() throws AWTException {
        onRoute = true;
        findImageHard = new FindPixels();
        mouse = Mouse.getInstance();
        capture = Capture.instance();
        actions = Actions.instance();
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

    public boolean checkLocationY(BufferedImage screenShot, Route route) throws
            InterruptedException {

        int[] checkCoords = route.getCheckCoords();
        LoggerSingle.logDebug(this.toString(),"checking location " + this.toString());
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

            LoggerSingle.logInfo(this.toString(), "Checheck location Y " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            SleepTime.sleep(200);
            return (y == checkCoords[1]);
        }
        return false;
    }

    public boolean checkLocationX(BufferedImage screenShot, Route route) throws
            InterruptedException {

        int[] checkCoords = route.getCheckCoords();
        LoggerSingle.logDebug(this.toString(),"checking location " + this.toString());
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

            LoggerSingle.logInfo(this.toString(), "Checheck location X " + this.toString() + ", coordinates: x=" + x + " y=" + y);            SleepTime.sleep(200);
            return (x == checkCoords[0]);
        }
        return false;
    }

    public boolean checkLocationTP(BufferedImage screenShot, int[] area) throws
            InterruptedException {

        LoggerSingle.logDebug(this.toString(),"checking location " + this.toString());
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

            LoggerSingle.logInfo(this.toString(), "Location TP " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            SleepTime.sleep(200);
            return true;
        }
        return false;
    }

    public abstract void tpUntil() throws Exception;

    private void moveByX(Route route, int[] clickXy) throws Exception {
        BufferedImage screenshot = capture.takeScreenShot();
        while (!checkLocationX(screenshot, route)) {
            mouse.mouseClick(clickXy[0], clickXy[1]);
            SleepTime.sleep(500);
            screenshot = capture.takeScreenShot();
            if (checkCount()) {
                break;
            }
        }
        if (checkCount()) {
            count = 0;
            this.tpUntil();
        }
        count = 0;
    }

    private void moveByY(Route route, int[] clickXy) throws Exception{
        BufferedImage screenshot = capture.takeScreenShot();
        while (!checkLocationY(screenshot, route)) {
            mouse.mouseClick(clickXy[0], clickXy[1]);
            SleepTime.sleep(500);
            screenshot = capture.takeScreenShot();
            if (checkCount()) {
                break;
            }
        }
        if (checkCount()) {
            count = 0;
            this.tpUntil();
            return;
        }
        count = 0;
    }

    private boolean checkCount() {
        count++;
        System.out.println(count);
        if (count > 100) {
            return true;
        }
        return false;
    }

    public void moveLeft(Route route) throws Exception {
        moveByX(route, new int[] {300, 461});
    }

    public void moveRight(Route route) throws Exception {
        moveByX(route, new int[] {880, 461});
    }

    public void moveUp(Route route) throws Exception {
        moveByY(route, new int[] {805, 200});
    }

    public void moveDown(Route route) throws Exception {
        moveByY(route, new int[] {805, 700});
        checkCount();
    }

    public void moveLeftSlowly(Route route) throws Exception {
        moveByX(route, new int[] {700, 461});
    }

    public void moveRightSlowly(Route route) throws Exception {
        moveByX(route, new int[] {880, 461});
    }

    public void moveUpSlowly(Route route) throws Exception {
        moveByY(route, new int[] {805, 420});
    }

    public void moveDownSlowly(Route route) throws Exception {
        moveByY(route, new int[] {805, 550});
    }

}
