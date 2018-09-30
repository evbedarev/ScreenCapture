package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.GefField05;
import logic.attacks.AttackGef05;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.*;
import logic.take_loot.*;
import main.Prop;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicGefField05 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);


    public LogicGefField05() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef05();
        actions = Actions.instance();
        locationCheck = new LocationCheck(new GefField05());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of(
                        new ThiefBug(),
                        new Creamy(),
                        new Smokie()
                ).collect(Collectors.toList());

        usefulLoot = new TakeLoot[] {
                new Card(),
            new Clothes(),
                new Shield(),
//            new Mask(logger),
                new Coupon()
        };

        loot = new TakeLoot[] {
//            new PowderOfButterfly(logger),
        };
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        moveByCard.move(locationCheck, killMonsterList, this);

//        locationCheck.locationCheck();
//        checkSP.enoghtSP();
//        killMonsterList.forEach(this::findAndKill);
//        checkMyHp();
//        actions.pickUpCard();
//        actions.pickUpLoot(locationCheck);
//        teleport();
//        count++;
//        Prop.cast.cast();
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
