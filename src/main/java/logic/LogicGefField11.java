package logic;

import actions.Actions;
import checks.CheckHP;
import checks.location.GefField11;
import checks.LocationCheck;
import logic.kill_monster.*;
import logic.take_loot.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicGefField11 extends LogicLocation {
    private static final int COUNT_OF_ATTACKS = 100;
    private final int threadId;
    private final CheckHP checkHP = new CheckHP(true);
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);

    private final TakeLoot[] usefulLoot = new TakeLoot[] {
            new Card(logger),
            new Card1(logger),
            new Clothes(logger),
            new Shield(logger),
            new Mask(logger),
            new Coupon(logger)
    };

    private final TakeLoot[] loot = new TakeLoot[] {
            new Honey(logger),
            new Scell(logger)
    };

    public LogicGefField11(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new Attack(logger);
        attack2 = new Attack2(logger);
        this.threadId = threadId;
        actions = Actions.instance();
        locationCheck = new LocationCheck(new GefField11(), logger);

        killMonsterList = Stream
                .of(new Goblin(logger), new GoblinLeader(logger))
                .collect(Collectors.toList());

    }

    public void mainHandle() throws Exception {
        if (threadId == 0) {
            locationCheck.locationCheck();
            if (count == 0)
               actions.stepAside(locationCheck);
            killMonsterList.forEach(this::findAndKill);
//            findAndKill();
            checkMyHp();
            actions.pickUpCard(usefulLoot);
            actions.pickUpLoot(loot);
            teleport();
            count++;
            logger.debug("Increase count by 1, count=" + count);
            checkCast();
        }

        if (threadId == 1) {
            ATOMIC_GUARD.incrementAndGet();
            ATOMIC_AWAKENING.incrementAndGet();
            ATOMIC_DEFENDER.incrementAndGet();
            sleep(1000);
        }
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

    void checkMyHp() throws Exception {
        actions.pickUpCard(usefulLoot);
        checkHP.checkHp();
    }

    void runFromMonster() throws Exception {
//        if (awareMonster.kill()) {
//            logger.info("GOBLIN LEADER");
//            actions.teleport();
//        }
    }

    void teleport() throws Exception {
        runFromMonster();
        if (count > 10) {
            logger.info("TELEPORTING count=" + count);
            count = 0;
            logger.info("Set count to " + count);
            actions.teleport();
            actions.stepAside(locationCheck);
        }
    }
}
