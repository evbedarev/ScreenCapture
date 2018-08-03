package logic;

import actions.Actions;
import checks.CheckHP;
import checks.location.GefField11;
import checks.LocationCheck;
import logic.attacks.AttackGef11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.*;
import logic.take_loot.*;
import main.Prop;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicGefField11 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicGefField11(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef11();
        actions = Actions.instance();
        locationCheck = new LocationCheck(new GefField11());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, locationCheck);
        killMonsterList = Stream
                .of(
                        new Goblin()
                ).collect(Collectors.toList());

        usefulLoot = new TakeLoot[] {
                new Card(),
//            new Clothes(logger),
                new Shield(),
//                new Mask(),
                new Coupon()
        };

        loot = new TakeLoot[] {
//            new Scell(logger),
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
        Prop.cast.cast();
        if (checkDie.check()) {
            while (true) {
                Thread.sleep(5000);
            }
        }
        locationCheck.locationCheck();
        checkSP.enoghtSP();
        killMonsterList.forEach(this::findAndKill);
        checkMyHp();
        actions.pickUpCard();
        actions.pickUpLoot();
        teleport();
        count++;
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
            actions.pickUpLoot();
            count = 0;
            actions.teleport(locationCheck);
            actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }

    void runFromMonster() throws Exception {
    }
}
