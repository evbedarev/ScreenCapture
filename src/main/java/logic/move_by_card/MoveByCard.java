package logic.move_by_card;

import actions.Actions;
import actions.SleepTime;
import checks.LocationCheck;
import find_image.FindPixels;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import logic.LogicLocation;
import logic.kill_monster.KillMonster;
import logic.take_loot.TakeLoot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MoveByCard {
    private static MoveByCard instance;
    int coordsYUp, coordsYDown, coordXLeft, coordXRight;
    private Mouse mouse;
    private Actions actions;
    private FindPixels findImageHard;
    private LocationCheck locationCheck;
    private Capture capture;
    private BufferedImage screenShot;
    private Optional<int[]> xy, xy1, mouseClickCoord;
    List<KillMonster> killMonsters;

    private MoveByCard() throws AWTException {
        xy1 = Optional.empty();
        actions = Actions.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
        capture = Capture.instance();
    }

    static public MoveByCard getInstance() throws AWTException {
        if (instance == null) {
            instance = new MoveByCard();
        }
        return instance;
    }

    public void initialize(int[] coordsArr) {
        coordXLeft = coordsArr[0];
        coordXRight = coordsArr[1];
        coordsYUp = coordsArr[2];
        coordsYDown = coordsArr[3];
    }

    public int[] moveMouseDirectly(int x2, int y2) {
        double x, y, x1, y1;
        x1 = 800;
        y1 = 450;
        x2 = 800 + x2;
        System.out.println("Take y2 = " + y2);
        y2 = Math.abs(450 + y2);
        x = 800;
//
        if (x2 == 800 && y2 > 450) {
            return new int[] {800, 700};
        }

        if (x2 == 800 && y2 < 450) {
            return new int[] {800, 200};
        }

        if (x1 > x2)
            x = x2 - 400;

        if (x1 < x2)
            x = x2 + 400;

        y = ((x - x1)/(x2 - x1))*(y2 - y1) + y1;
//        System.out.println("x1 " + x1);
        System.out.println("x2 " + x2);
        System.out.println("y2 " + y2);

        if (y >= 900 || y < 0) {
            System.out.println("Calculate by Y");
            x1 = 800;
            y1 = 450;
            y = 450;

            if (y1 > y2)
                y = y2 - 300;

            if (y1 < y2)
                y = y2 + 300;

            x = (((y - y1)/(y2-y1))*(x2 - x1) + x1);
        } else {
            System.out.println("Calculate by X");
        }

        System.out.println(Math.abs(x) + "," + Math.abs(y));
        return new int[] {(int)x, (int)y};
    }

    public boolean moveToPoint(int[] point, List<KillMonster> killMonsterlist, LogicLocation logicLocation) throws Exception {
        xy = takeCoordsFromMap();

        if (!xy.isPresent())
            return false;

        System.out.println("My cooord X is :" + xy.get()[0]);
        System.out.println("My cooord Y is :" + xy.get()[1]);
//
        System.out.println("Going to point cooord X is :" + point[0]);
        System.out.println("Going to point cooord Y is :" + point[1]);


        while (Math.abs(xy.get()[0] - point[0]) > 2 | Math.abs(xy.get()[1] - point[1]) > 2) {


            int[] coords = moveMouseDirectly(point[0] - xy.get()[0], point[1] - xy.get()[1]);
            System.out.println("Mouse cooord X is :" + coords[0]);
            System.out.println("Mouse cooord Y is :" + coords[1]);
            mouse.mouseClick(coords[0], coords[1]);

            SleepTime.sleep(500);
            for (KillMonster killMonster : killMonsterlist) {
                logicLocation.findAndKill(killMonster);
            }
            xy = takeCoordsFromMap();
            System.out.println("My cooord X is :" + xy.get()[0]);
            System.out.println("My cooord Y is :" + xy.get()[1]);
        }

        System.out.println("Arrive to point: " + point[0] + ", " + point[1]);
        return true;
    }

    public void move(LocationCheck locationCheck,  List<KillMonster> killMonsters, LogicLocation logicLocation) throws Exception {
        List<int[]> points = new ArrayList<>();
        points.add(new int[] {1471,150});
        points.add(new int[] {1492,150});
        points.add(new int[] {1504,151});
        points.add(new int[] {1516,151});
        points.add(new int[] {1525,154});
        points.add(new int[] {1551,146});
        points.add(new int[] {1562,136});
        points.add(new int[] {1554,127});
        points.add(new int[] {1566,116});
        points.add(new int[] {1561,96});
        points.add(new int[] {1564,77});
        points.add(new int[] {1551,73});
        points.add(new int[] {1544,83});

        for (int[] point : points) {
            moveToPoint(point, killMonsters, logicLocation);
        }

        Collections.reverse(points);

        for (int[] point : points) {
            moveToPoint(point, killMonsters, logicLocation);
        }

//        this.locationCheck = locationCheck;
//        this.killMonsters = killMonsters;
//
//        if (checkLocationYUP(coordsYUp))
//            moveDown();
//
//        if (checkLocationYDown(coordsYDown))
//            moveUp();
//
//        if (checkLocationXLeft(coordXLeft))
//            moveRight();
//
//        if (checkLocationXRight(coordXRight))
//            moveLeft();
//
//        xy = takeCoordsFromMap();
//        if (!xy1.isPresent()) {
//            mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
//            xy1 = takeCoordsFromMap();
//        }
//
//        SleepTime.sleep(200);
//        if ((xy.get()[0] != xy1.get()[0]) || (xy.get()[1] != xy1.get()[1])) {
//            mouse.mouseClick(mouseClickCoord.get()[0], mouseClickCoord.get()[1]);
//        } else {
//            mouseClickCoord =actions.stepAside(locationCheck, new int[]{500, 600}, true);
//        }
//
//        SleepTime.sleep(200);
//        xy1 = takeCoordsFromMap();
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

//            LoggerSingle.logInfo(this.toString(), "Location TP " + this.toString() + ", coordinates: x=" + x + " y=" + y);
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
        Optional<int[]> y, y1;
        centredMap();
        SleepTime.sleep(200);
        LoggerSingle.logInfo(this.toString(), this.toString() + " Move Down");
        while (!checkLocationYDown(coordsYUp + ((coordsYDown - coordsYUp)/2))) {
            y = takeCoordsFromMap();
            mouse.mouseClick(805, 700);
            SleepTime.sleep(500);
            y1 = takeCoordsFromMap();

            if (!y.isPresent() || !y1.isPresent())
                break;

            if (y.get()[0] == y1.get()[0])
                break;

            for (KillMonster killMonster: killMonsters) {
                killMonster.findAndKill();
            }
        }

        mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
    }


    public void moveUp() throws Exception {
        Optional<int[]> y, y1;
        centredMap();
        SleepTime.sleep(200);
        LoggerSingle.logInfo(this.toString(), this.toString() + " Move Up");
        while (!checkLocationYUP(coordsYUp + ((coordsYDown - coordsYUp)/2))) {
            y = takeCoordsFromMap();
            mouse.mouseClick(805, 200);
            SleepTime.sleep(500);
            y1 = takeCoordsFromMap();

            if (!y.isPresent() || !y1.isPresent())
                break;

            if (y.get()[0] == y1.get()[0])
                break;

            for (KillMonster killMonster: killMonsters) {
                killMonster.findAndKill();
            }
        }
        mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
    }


    public void moveLeft() throws Exception {
        Optional<int[]> x, x1;
        centredMap();
        SleepTime.sleep(200);
        LoggerSingle.logInfo(this.toString(), this.toString() + " Move Left");
        while (!checkLocationXLeft(coordXLeft + ((coordXRight - coordXLeft)/2))) {
            x = takeCoordsFromMap();
            mouse.mouseClick(300, 461);
            SleepTime.sleep(500);
            x1 = takeCoordsFromMap();

            if (!x.isPresent() || !x1.isPresent())
                break;
            if (x.get()[0] == x1.get()[0])
                break;

            for (KillMonster killMonster: killMonsters) {
                killMonster.findAndKill();
            }
        }
        mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
    }

    public void moveRight() throws Exception {
        Optional<int[]> x, x1;
        centredMap();
        SleepTime.sleep(200);
        LoggerSingle.logInfo(this.toString(), this.toString() + " Move Right");
        while (!checkLocationXRight(coordXLeft + ((coordXRight - coordXLeft)/2))) {
            x = takeCoordsFromMap();
            mouse.mouseClick(1200, 461);
            SleepTime.sleep(500);
            x1 = takeCoordsFromMap();

            if (!x.isPresent() || !x1.isPresent())
                break;

            if (x.get()[0] == x1.get()[0])
                break;

            for (KillMonster killMonster: killMonsters) {
                killMonster.findAndKill();
            }
        }
        mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
    }

    private void centredMap() throws InterruptedException {
        mouse.pressRight();
        SleepTime.sleep(50);
        mouse.releaseRight();
        SleepTime.sleep(100);
        mouse.pressRight();
        SleepTime.sleep(50);
        mouse.releaseRight();
    }
}
