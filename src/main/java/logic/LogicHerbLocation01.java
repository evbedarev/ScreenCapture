package logic;

import actions.Actions;
import actions.SleepTime;
import checks.LocationCheck;
import checks.location.PrtField08;
import find_image.FindPixels;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.attacks.AttackGef11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.*;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsHerbLocation1;
import logic.screen_shot.Capture;
import logic.screen_shot.ScreenShot;
import logic.take_loot.TakeLoot;
import main.Prop;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicHerbLocation01 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    Mouse mouse;
    private MoveByCard moveByCard;
    Optional<int[]> xy, xy1, mouseClickCoord;

    public LogicHerbLocation01() throws Exception {
        xy1 = Optional.empty();
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef11();
        moveByCard = MoveByCard.getInstance(this, new PointsHerbLocation1());
        actions = Actions.instance();
        locationCheck = new LocationCheck(new PrtField08());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersHerbLocation01");
        usefulLoot = (TakeLoot[]) Prop.context.getBean("usefulHerbLocation01");
        loot = (TakeLoot[]) Prop.context.getBean("lootHerbLocation01");
        checkAgressorIsNear.initialize(Stream
                .of(new Goblin())
                .collect(Collectors.toList()));
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        moveByCard.move(killMonsterList);
    }

    public void checkMyHp() throws Exception {
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
        BufferedImage screenShot = Prop.context.getBean(ScreenShot.class).pop();
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
