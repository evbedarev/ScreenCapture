package logic.move_by_card;

import actions.Actions;
import actions.SleepTime;
import checks.LocationCheck;
import find_image.FindPixels;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import routes.RouteModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class MoveByCard {
    private static MoveByCard instance;
    int coordsYUp, coordsYDown, coordXLeft, coordXRight;
    private Mouse mouse;
    private Actions actions;
    private FindPixels findImageHard;
    private Capture capture;
    private BufferedImage screenShot;
    private Optional<int[]> xy, xy1, mouseClickCoord;

    private MoveByCard() throws AWTException {
        xy1 = Optional.empty();
        actions = Actions.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
        capture = Capture.instance();
        coordsYUp = 58;
        coordsYDown = 121;
        coordXLeft = 1484;
        coordXRight = 1538;
    }

    static public MoveByCard getInstance() throws AWTException {
        if (instance == null) {
            instance = new MoveByCard();
        }
        return instance;
    }

    public void move(LocationCheck locationCheck) throws Exception {
        if (checkLocationYUP(coordsYUp))
            moveDown();

        if (checkLocationYDown(coordsYDown))
            moveUp();

        if (checkLocationXLeft(coordXLeft))
            moveRight();

        if (checkLocationXRight(coordXRight))
            moveLeft();

        xy = takeCoordsFromMap();
        if (!xy1.isPresent()) {
            mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
            xy1 = takeCoordsFromMap();
        }

        SleepTime.sleep(500);
        if ((xy.get()[0] != xy1.get()[0]) || (xy.get()[1] != xy1.get()[1])) {
            mouse.mouseClick(mouseClickCoord.get()[0], mouseClickCoord.get()[1]);
        } else {
            mouseClickCoord =actions.stepAside(locationCheck, new int[]{500, 600}, true);
        }

        SleepTime.sleep(500);
        xy1 = takeCoordsFromMap();
    }

    private Optional<int[]> takeCoordsFromMap() throws Exception {
        screenShot = capture.takeScreenShot();
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {1459, 1586, 43, 168});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];

            LoggerSingle.logInfo(this.toString(), "Location TP " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            SleepTime.sleep(200);
        }
        return xy;
    }

    private boolean checkLocationYUP(int coord) throws Exception {
        screenShot = capture.takeScreenShot();
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {1459, 1586, 43, 168});

        if (xy.isPresent()) {
            int y = xy.get()[1];
            return y < coord;
        }
        return false;
    }

    private boolean checkLocationYDown(int coord) throws Exception {
        screenShot = capture.takeScreenShot();
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {1459, 1586, 43, 168});

        if (xy.isPresent()) {
            int y = xy.get()[1];
            return y > coord;
        }
        return false;
    }


    private boolean checkLocationXLeft(int coord) throws Exception {
        screenShot = capture.takeScreenShot();
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {1459, 1586, 43, 168});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            return x < coord;
        }
        return false;
    }

    private boolean checkLocationXRight(int coord) throws Exception {
        screenShot = capture.takeScreenShot();
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {1459, 1586, 43, 168});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            return x > coord;
        }
        return false;
    }



    public void moveDown() throws Exception {
        centredMap();
        SleepTime.sleep(200);
        while (!checkLocationYDown(coordsYUp + ((coordsYDown - coordsYUp)/2))) {

            mouse.mouseClick(805, 700);
            SleepTime.sleep(500);
        }
    }


    public void moveUp() throws Exception {
        centredMap();
        SleepTime.sleep(200);
        while (!checkLocationYUP(coordsYUp + ((coordsYDown - coordsYUp)/2))) {
            mouse.mouseClick(805, 200);
            SleepTime.sleep(500);
        }
    }


    public void moveLeft() throws Exception {
        Optional<int[]> x, x1;
        centredMap();
        SleepTime.sleep(200);
        while (!checkLocationXLeft(coordXLeft + ((coordXRight - coordXLeft)/2))) {
            x = takeCoordsFromMap();
            mouse.mouseClick(300, 461);
            SleepTime.sleep(500);
            x1 = takeCoordsFromMap();
            if (x.get()[0] == x1.get()[0])
                break;

        }
    }

    public void moveRight() throws Exception {
        Optional<int[]> x, x1;
        centredMap();
        SleepTime.sleep(200);
        while (!checkLocationXRight(coordXLeft + ((coordXRight - coordXLeft)/2))) {
            x = takeCoordsFromMap();
            mouse.mouseClick(1200, 461);
            SleepTime.sleep(500);
            x1 = takeCoordsFromMap();
            if (x.get()[0] == x1.get()[0])
                break;
        }
    }

    private void centredMap() throws InterruptedException {
        mouse.pressRight();
        SleepTime.sleep(50);
        mouse.releaseRight();
        SleepTime.sleep(50);
        mouse.pressRight();
        SleepTime.sleep(50);
        mouse.releaseRight();
    }
}
