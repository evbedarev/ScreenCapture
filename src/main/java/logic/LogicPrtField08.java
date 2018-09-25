package logic;

import actions.Actions;
import actions.SleepTime;
import checks.LocationCheck;
import checks.location.GefField11;
import checks.location.PrtField08;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.attacks.AttackGef11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.Goblin;
import logic.kill_monster.Poring;
import logic.take_loot.*;
import main.Prop;

import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicPrtField08 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    Keys keys = Keys.getInstance();
    Mouse mouse;
    Optional<int[]> xy, xy1, mouseClickCoord;

    public LogicPrtField08(int threadId) throws Exception {
        xy1 = Optional.empty();
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef11();
        actions = Actions.instance();
        locationCheck = new LocationCheck(new PrtField08());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of(
                        new Poring()
                ).collect(Collectors.toList());

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Coupon()
        };

        loot = new TakeLoot[] {
            new Bottle(),
        };

        checkAgressorIsNear.initialize(Stream
                .of(new Goblin())
                .collect(Collectors.toList()));
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        mouse = Mouse.getInstance();
        xy = takeCoordsFromMap();
        if (!xy1.isPresent()) {
            mouseClickCoord = actions.stepAside(locationCheck, new int[]{500, 800}, true);
            xy1 = takeCoordsFromMap();
        }

        SleepTime.sleep(1000);
        if ((xy.get()[0] != xy1.get()[0]) || (xy.get()[1] != xy1.get()[1])) {
            System.out.println(mouseClickCoord.get()[0]);
            System.out.println(mouseClickCoord.get()[1]);
            mouse.mouseClick(mouseClickCoord.get()[0], mouseClickCoord.get()[1]);
        } else {
            mouseClickCoord =actions.stepAside(locationCheck, new int[]{500, 800}, true);
        }
        xy1 = takeCoordsFromMap();

        killMonsterList.forEach(this::findAndKill);
//        checkMyHp();
//        actions.pickUpCard();
//        actions.pickUpLoot(locationCheck);
//        count++;
    }

    void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }


    void teleport() throws Exception {
        runFromMonster();
        if (count > Prop.COUNT_TO_TELEPORT) {
            lootAround.takeLootAround();
            sleep(500);
            actions.pickUpCard();
            actions.pickUpLoot(locationCheck);
            count = 0;
            actions.teleport(locationCheck);
            actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }


    private Optional<int[]> takeCoordsFromMap() throws Exception {
        FindPixels findImageHard = new FindPixels();
        Capture capture = Capture.instance();
        BufferedImage screenShot = capture.takeScreenShot();
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

    void runFromMonster() throws Exception {
    }
}