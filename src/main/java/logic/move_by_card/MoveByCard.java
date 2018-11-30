package logic.move_by_card;

import actions.Actions;
import actions.InterfaceActions;
import actions.SleepTime;
import checks.afterDeath.AfterDeath;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import logic.LogicLocation;
import logic.RgbParameter;
import logic.kill_monster.Human;
import logic.kill_monster.KillMonster;
import main.Prop;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * Класс который отлеживает передвижение по карте, перемещает персонажа по заданным точкам
 */

public class MoveByCard {
    private static MoveByCard instance;
    int coordsYUp, coordsYDown, coordXLeft, coordXRight;
    private static Mouse mouse;
    private static InterfaceActions interfaceActions;
    private static Actions actions;
    private static FindPixels findImageHard;
    private static LogicLocation logicLocation;
    private static Capture capture;
    private static BufferedImage screenShot;
    private static Optional<int[]> xy;
    private static AfterDeath checkDie = Prop.checkDie;
    private static Keys keys;
    private static Points pointsOnCard;
    private static PointsFindNearest findNearest = new PointsFindNearest();
    private static boolean flagOfNewPoints = false;
    private int[] prevPos = new int[] {0,0};
    private Human human = new Human();

    private MoveByCard(LogicLocation logicLocation, Points pointsOnCard) throws AWTException {
        actions = Actions.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
        capture = Capture.instance();
        MoveByCard.logicLocation = logicLocation;
        interfaceActions = InterfaceActions.getInstance();
        MoveByCard.pointsOnCard = pointsOnCard;
        findNearest.setPoints(pointsOnCard.getPoints());
    }

    public static MoveByCard getInstance(LogicLocation logicLocation, Points pointsOnCard) throws AWTException {
        if (instance == null) {
            instance = new MoveByCard(logicLocation, pointsOnCard);
        }
        return instance;
    }

    public void initialize(int[] coordsArr) throws AWTException {
        coordXLeft = coordsArr[0];
        coordXRight = coordsArr[1];
        coordsYUp = coordsArr[2];
        coordsYDown = coordsArr[3];
    }

    /**
     * Вычисляет положение курсора для дальнейшего передвижения к заданной точке
     * @param x2 - расстояние по оси Х от текущего положения до следующей точки
     * @param y2 - расстояние по оси Y от текущего положения до следующей точки
     * @return - возвращает координаты на которые должен быть перемещен курсор для движения
     * в сторону следующей точки.
     */

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

    public boolean moveToPoint(int[] point, List<KillMonster> killMonsterlist) {
        try {
            xy = takeCoordsFromMap();
            keys = Keys.getInstance();
            LoggerSingle.logInfo(this.toString(), "Going to point cooords : {" + point[0] + ", " + point[1] + "}");

            if (!xy.isPresent()) {
                wingAway();
                LoggerSingle.logInfo(this.toString(), "No location coords value present! Use wing");
                return false;
            }
            actions.checkResources(screenShot);
            while (Math.abs(xy.get()[0] - point[0]) > 2 | Math.abs(xy.get()[1] - point[1]) > 2) {
                Prop.cast.cast();
                logicLocation.getLocationCheck().locationCheck();
                int[] coords = moveMouseDirectly(point[0] - xy.get()[0], point[1] - xy.get()[1]);
                screenShot = capture.takeScreenShot();
                mouse.mouseClick(coords[0], coords[1]);
                logicLocation.checkMyHp(screenShot);
                if (checkDialogWindow(screenShot)) {
//                    actions.stepAside(new int[] {400, 500});
                    wingAway();
                    break;
                }
//                if (human.findMonster(screenShot)) {
//                    wingAway();
//                    break;
//                }
                LoggerSingle.logInfo(this.toString(), "Go to point: " + point[0] + ", " + point[1]);
//                actions.pickUpCard(screenShot);

                for (KillMonster killMonster : killMonsterlist) {
                    logicLocation.findAndKill(killMonster);
                }

                if (prevPos[0] == xy.get()[0] && prevPos[1] == xy.get()[1]) {
                    wingAway();
                    LoggerSingle.logInfo(this.toString(), "Don't moving. Wing away");
                    break;
                } else {
                    prevPos = new int[] {xy.get()[0],  xy.get()[1]};
                }
                checkDie.check(screenShot);
                xy = takeCoordsFromMap();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            while (true) {
                    SleepTime.sleep(5000);
            }
        }
        return true;
    }

    /**
     * Проверяет нет ли диалога, если есть то закрывает его.
     * @param image - скриншот
     * @return - возвращает был ли диалог.
     * @throws Exception
     */
    private boolean checkDialogWindow(BufferedImage image) throws Exception {
        boolean wasDialog = false;
        while (interfaceActions.pressNext(image) || interfaceActions.pressClose(image) || interfaceActions.pressOk(image)) {
            image = capture.takeScreenShot();
            mouse.mouseMove(0,0);
            SleepTime.sleep(500);
            LoggerSingle.logInfo(this.toString(), "In dialog");
            wasDialog = true;
        }
        return wasDialog;
    }

    /**
     * метод который телепортирует в случайную точку.
     * Обязательно после имплементации использовать break;
     * @throws Exception
     */
    public static void wingAway() throws Exception {
        actions.useWing(logicLocation.getLocationCheck());
        SleepTime.sleep(1000);
        xy = takeCoordsFromMap();
        flagOfNewPoints = true;
        LoggerSingle.logInfo("MoveByClass", " Use wing.");
    }

    public void move(List<KillMonster> killMonsters) throws Exception {
        List<int[]> points = pointsOnCard.getPoints();
        for (int[] point : points) {
            if (flagOfNewPoints) {
                if (!xy.isPresent()) {
                    wingAway();
                    LoggerSingle.logInfo(this.toString(), "No location coords value present! Use wing");
                    continue;
                }
                flagOfNewPoints = false;
                LoggerSingle.logInfo(this.toString(), "Coordinates after teleporting is : " + xy.get()[0] + "," + xy.get()[1]);
                pointsOnCard.setPoints(findNearest.findNearestPoint(new int[] {xy.get()[0], xy.get()[1]}));
                for (int[] ints : pointsOnCard.getPoints()) {
                    LoggerSingle.logInfo(this.toString(),"Values of New List is: " + ints[0] + "," + ints[1]);
                }
                break;
            }
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

    private static Optional<int[]> takeCoordsFromMap() throws Exception {
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


