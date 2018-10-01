package logic.move_by_card;

import actions.Actions;
import actions.SleepTime;
import checks.LocationCheck;
import checks.afterDeath.AfterDeath;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import logic.LogicLocation;
import logic.RgbParameter;
import logic.kill_monster.KillMonster;
import main.Prop;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private LogicLocation logicLocation;
    private Capture capture;
    private BufferedImage screenShot;
    private Optional<int[]> xy, xy1, mouseClickCoord;
    List<KillMonster> killMonsters;
    private AfterDeath checkDie = Prop.checkDie;
    private Keys keys;

    private MoveByCard(LogicLocation logicLocation) throws AWTException {
        xy1 = Optional.empty();
        actions = Actions.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
        capture = Capture.instance();
        this.logicLocation = logicLocation;
    }

    static public MoveByCard getInstance(LogicLocation logicLocation) throws AWTException {
        if (instance == null) {
            instance = new MoveByCard(logicLocation);
        }
        return instance;
    }

    public void initialize(int[] coordsArr) throws AWTException {
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
        LoggerSingle.logDebug(this.toString(), "Take y2 = " + y2);
        y2 = Math.abs(450 + y2);
        x = 800;

        if (x2 == 800 && y2 > 450) {
            return new int[] {800, 800};
        }
        if (x2 == 800 && y2 < 450) {
            return new int[] {800, 100};
        }

        if (x1 > x2)
            x = x2 - 400;
        if (x1 < x2)
            x = x2 + 400;

        y = ((x - x1)/(x2 - x1))*(y2 - y1) + y1;
        LoggerSingle.logDebug(this.toString(),"Calculated x2 " + x2);
        LoggerSingle.logDebug(this.toString(),"Calculated y2 " + y2);

        if (y >= 900 || y < 0) {
            LoggerSingle.logDebug(this.toString(),"Calculated by Y ");
            x1 = 800;
            y1 = 450;
            y = 450;

            if (y1 > y2)
                y = y2 - 350;

            if (y1 < y2)
                y = y2 + 350;

            x = (((y - y1)/(y2-y1))*(x2 - x1) + x1);
        } else {
            LoggerSingle.logDebug(this.toString(),"Calculated by X ");
        }
        LoggerSingle.logDebug(this.toString(),"Result values: " + Math.abs(x) + "," + Math.abs(y));
        return new int[] {(int)x, (int)y};
    }

    public boolean moveToPoint(int[] point, List<KillMonster> killMonsterlist) throws Exception {
        xy = takeCoordsFromMap();
        keys = Keys.getInstance();
        int countMoves = 0;

        LoggerSingle.logDebug(this.toString(),"My cooord X is :" + xy.get()[0]);
        LoggerSingle.logDebug(this.toString(),"My cooord Y is :" + xy.get()[1]);
        LoggerSingle.logDebug(this.toString(),"Going to point cooord X is :" + point[0]);
        LoggerSingle.logDebug(this.toString(),"Going to point cooord Y is :" + point[1]);

        if (!xy.isPresent())
            return false;

        while (Math.abs(xy.get()[0] - point[0]) > 2 | Math.abs(xy.get()[1] - point[1]) > 2) {
            int[] coords = moveMouseDirectly(point[0] - xy.get()[0], point[1] - xy.get()[1]);
            LoggerSingle.logDebug(this.toString(),"Mouse cooord X is :" + coords[0]);
            LoggerSingle.logDebug(this.toString(),"Mouse cooord Y is :" + coords[1]);

            mouse.mouseMove(coords[0], coords[1]);
            SleepTime.sleep(200);

            screenShot = capture.takeScreenShot();
            mouse.mouseClick(coords[0], coords[1]);

            for (KillMonster killMonster : killMonsterlist) {
               while (killMonster.findMonster()) {
                   keys.keyPress(Prop.SPELL_ATTACK_KEY);
                   killMonster.findAndKill();
                   logicLocation.checkMyHp();
                   checkDie.check();
                   SleepTime.sleep(2000);
               }
//             logicLocation.findAndKill(killMonster);
            }

            if (countMoves > 10) {
                actions.useWing();
            }

            logicLocation.checkMyHp();
            checkDie.check();

            xy = takeCoordsFromMap();
//            System.out.println("My cooord X is :" + xy.get()[0]);
//            System.out.println("My cooord Y is :" + xy.get()[1]);
            countMoves++;
        }

//        System.out.println("Arrive to point: " + point[0] + ", " + point[1]);
        return true;
    }

    public void move(List<KillMonster> killMonsters, Points pointsOnCard) throws Exception {
        List<int[]> points = pointsOnCard.getPoints();

        for (int[] point : points) {
            moveToPoint(point, killMonsters);
        }

        Collections.reverse(points);

        for (int[] point : points) {
            moveToPoint(point, killMonsters);
        }
    }

    /**
     * Проверяет есть ли под курсором моб
     * @param coords - координаты курсора
     * @param killMonsterList - список монстров
     * @return
     */
    private boolean checkMonsterUnderCoursor(int[] coords, List<KillMonster> killMonsterList) {
        List<RgbParameter> rgbParameter;
        screenShot = capture.takeScreenShot();

        for (KillMonster killMonster : killMonsterList) {
            rgbParameter = killMonster.getRgbParameterList();
            for (RgbParameter parameter : rgbParameter) {
                Optional<int[]> coordsCoursor = findImageHard.findPixelsInImageInArea(
                        screenShot,
                        parameter.getMainRgb(),
                        parameter.getSubImageSize(),
                        parameter.getAncillaryRgb(),
                        new int[] {coords[0] - 40, coords[0] + 40, coords[1] - 40, coords[1] + 40});

                if (coordsCoursor.isPresent())
                    return true;
            }
        }
        return false;
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
}


//////////PRONTERA 8 /////////////////////////////////
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
//////////////////////////////////////////

//    private boolean checkLocationYUP(int coord) throws Exception {
//        screenShot = capture.takeScreenShot();
//        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
//                screenShot,
//                -2752512,
//                new int[] {2,2},
//                new int[] {-2752512},
//                new int[] {1459, 1586, 43, 168});
//
//        if (xy.isPresent()) {
//            int y = xy.get()[1];
//            return y < coord;
//        }
//        return false;
//    }
//
//    private boolean checkLocationYDown(int coord) throws Exception {
//        screenShot = capture.takeScreenShot();
//        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
//                screenShot,
//                -2752512,
//                new int[] {2,2},
//                new int[] {-2752512},
//                new int[] {1459, 1586, 43, 168});
//
//        if (xy.isPresent()) {
//            int y = xy.get()[1];
//            return y > coord;
//        }
//
//        return false;
//    }
//
//
//    private boolean checkLocationXLeft(int coord) throws Exception {
//        screenShot = capture.takeScreenShot();
//        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
//                screenShot,
//                -2752512,
//                new int[] {2,2},
//                new int[] {-2752512},
//                new int[] {1459, 1586, 43, 168});
//
//        if (xy.isPresent()) {
//            int x = xy.get()[0];
//            return x < coord;
//        }
//        return false;
//    }
//
//    private boolean checkLocationXRight(int coord) throws Exception {
//        screenShot = capture.takeScreenShot();
//        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
//                screenShot,
//                -2752512,
//                new int[] {2,2},
//                new int[] {-2752512},
//                new int[] {1459, 1586, 43, 168});
//
//        if (xy.isPresent()) {
//            int x = xy.get()[0];
//            return x > coord;
//        }
//        return false;
//    }
//
//
//
//    public void moveDown() throws Exception {
//        Optional<int[]> y, y1;
//        centredMap();
//        SleepTime.sleep(200);
//        LoggerSingle.logInfo(this.toString(), this.toString() + " Move Down");
//        while (!checkLocationYDown(coordsYUp + ((coordsYDown - coordsYUp)/2))) {
//            y = takeCoordsFromMap();
//            mouse.mouseClick(805, 700);
//            SleepTime.sleep(500);
//            y1 = takeCoordsFromMap();
//
//            if (!y.isPresent() || !y1.isPresent())
//                break;
//
//            if (y.get()[0] == y1.get()[0])
//                break;
//
//            for (KillMonster killMonster: killMonsters) {
//                killMonster.findAndKill();
//            }
//        }
//
//        mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
//    }
//
//
//    public void moveUp() throws Exception {
//        Optional<int[]> y, y1;
//        centredMap();
//        SleepTime.sleep(200);
//        LoggerSingle.logInfo(this.toString(), this.toString() + " Move Up");
//        while (!checkLocationYUP(coordsYUp + ((coordsYDown - coordsYUp)/2))) {
//            y = takeCoordsFromMap();
//            mouse.mouseClick(805, 200);
//            SleepTime.sleep(500);
//            y1 = takeCoordsFromMap();
//
//            if (!y.isPresent() || !y1.isPresent())
//                break;
//
//            if (y.get()[0] == y1.get()[0])
//                break;
//
//            for (KillMonster killMonster: killMonsters) {
//                killMonster.findAndKill();
//            }
//        }
//        mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
//    }
//
//
//    public void moveLeft() throws Exception {
//        Optional<int[]> x, x1;
//        centredMap();
//        SleepTime.sleep(200);
//        LoggerSingle.logInfo(this.toString(), this.toString() + " Move Left");
//        while (!checkLocationXLeft(coordXLeft + ((coordXRight - coordXLeft)/2))) {
//            x = takeCoordsFromMap();
//            mouse.mouseClick(300, 461);
//            SleepTime.sleep(500);
//            x1 = takeCoordsFromMap();
//
//            if (!x.isPresent() || !x1.isPresent())
//                break;
//            if (x.get()[0] == x1.get()[0])
//                break;
//
//            for (KillMonster killMonster: killMonsters) {
//                killMonster.findAndKill();
//            }
//        }
//        mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
//    }
//
//    public void moveRight() throws Exception {
//        Optional<int[]> x, x1;
//        centredMap();
//        SleepTime.sleep(200);
//        LoggerSingle.logInfo(this.toString(), this.toString() + " Move Right");
//        while (!checkLocationXRight(coordXLeft + ((coordXRight - coordXLeft)/2))) {
//            x = takeCoordsFromMap();
//            mouse.mouseClick(1200, 461);
//            SleepTime.sleep(500);
//            x1 = takeCoordsFromMap();
//
//            if (!x.isPresent() || !x1.isPresent())
//                break;
//
//            if (x.get()[0] == x1.get()[0])
//                break;
//
//            for (KillMonster killMonster: killMonsters) {
//                killMonster.findAndKill();
//            }
//        }
//        mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 600}, true);
//    }
//
//    private void centredMap() throws InterruptedException {
//        mouse.pressRight();
//        SleepTime.sleep(50);
//        mouse.releaseRight();
//        SleepTime.sleep(100);
//        mouse.pressRight();
//        SleepTime.sleep(50);
//        mouse.releaseRight();
//    }


