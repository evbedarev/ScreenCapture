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
import logic.move_by_card.PointsMocField01;
import logic.move_by_card.PointsMocField11;
import logic.take_loot.*;
import main.Prop;

import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicMocField11 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    Mouse mouse;
    private MoveByCard moveByCard;
    Optional<int[]> xy, xy1, mouseClickCoord;

    public LogicMocField11() throws Exception {
        xy1 = Optional.empty();
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef11();
        moveByCard = MoveByCard.getInstance(this, new PointsMocField11());
        actions = Actions.instance();
        locationCheck = new LocationCheck(new PrtField08());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of(
                        new Scorpion(),
                        new BabyDesertWolf(),
                        new Condor()
                ).collect(Collectors.toList());

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Coupon(),
                new Clothes()
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

    void runFromMonster() throws Exception {
    }
}
