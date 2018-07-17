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
    private final int threadId;
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);


    public LogicGefField11(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef11(logger);
        this.threadId = threadId;
        actions = Actions.instance();
        locationCheck = new LocationCheck(new GefField11(), logger);
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, locationCheck);
        killMonsterList = Stream
                .of(
                        new Goblin(logger)
                ).collect(Collectors.toList());

        usefulLoot = new TakeLoot[] {
                new Card(),
//            new Clothes(logger),
                new Shield(),
                new Mask(),
                new Coupon()
        };

        loot = new TakeLoot[] {
//            new Scell(logger),
        };
    }

    @Override
    public void createThread() throws Exception {
        Thread thread = new LogicGefField11(1);
        thread.start();
        start();
    }

    public void mainHandle() throws Exception {
        if (threadId == 0) {
            locationCheck.locationCheck();
            checkSP.enoghtSP();
            killMonsterList.forEach(this::findAndKill);
            checkMyHp();
            actions.pickUpCard();
            actions.pickUpLoot();
            teleport();
            count++;
            checkCast();
        }

        if (threadId == 1) {
            ATOMIC_GUARD.incrementAndGet();
            ATOMIC_AWAKENING.incrementAndGet();
            ATOMIC_DEFENDER.incrementAndGet();
            sleep(1000);
        }
    }

    void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    void checkCast() throws InterruptedException {
        if (ATOMIC_GUARD.get() > 300) {
            actions.castGuard();
            actions.castReflectShield();
            ATOMIC_GUARD.set(0);
        }

        if (ATOMIC_AWAKENING.get() > 1800) {
            actions.drinkAwaikeningPotion();
            ATOMIC_AWAKENING.set(0);
        }

        if (ATOMIC_DEFENDER.get() > 180) {
//            keys.keyPress(DEFENDER);
            sleep(1000);
            ATOMIC_DEFENDER.set(0);
        }
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
