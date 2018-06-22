package logic;

import actions.Actions;
import checks.CheckHP;
import checks.location.GefField11;
import checks.LocationCheck;
import logic.kill_monster.*;
import logic.take_loot.*;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class LogicGefField11 extends Thread implements Logic {
    private int count = 0;
    private final int threadId;
    private final CheckHP checkHP = new CheckHP(true);
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);
    private Logger logger = Logger.getLogger(this.getClass());
    private Thread logicLocation = new LogicGefField11(1);

    private Attack attack;
    private Attack2 attack2;
    KillMonster goblin;
    KillMonster awareMonster;
    Actions actions;
    private LocationCheck locationCheck = new LocationCheck(new GefField11(), logger);

    private final TakeLoot[] usefulLoot = new TakeLoot[] {
            new Card(logger),
            new Card1(logger),
            new Clothes(logger),
            new Shield(logger),
            new Mask(logger)
    };

    private final TakeLoot[] loot = new TakeLoot[] {
            new Honey(logger),
            new Scell(logger)
    };

    public LogicGefField11(int threadId) throws Exception {
        goblin = new Goblin(logger);
        awareMonster = new GoblinLeader(logger);
        attack = new Attack(logger);
        attack2 = new Attack2(logger);
        this.threadId = threadId;
        actions = Actions.instance();
    }

    public void createThread() throws Exception {
        Thread thread = new LogicGefField11(1);
        thread.start();
        start();
    }

    public void run() {
        try {
            while (true) {
                mainHandle();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void mainHandle() throws Exception {
        if (threadId == 0) {
            locationCheck.locationCheck();
            if (count == 0)
               actions.stepAside(locationCheck);
            findAndKill();
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

    void findAndKill() throws Exception{
        while (goblin.kill())
        {
            count = 0;
            runFromMonster();
            logger.debug("Set count to " + count);
            checkMyHp();
            Thread.sleep(500);
            duringTheFight();
        }
        if (count == 0)
            actions.stepAside(locationCheck);
    }

    void duringTheFight() throws Exception {
        int atk = 1;
        while (attack.killOrNot() || attack2.killOrNot()) {
            count = 0;
            logger.debug("Set count to " + count);
            atk++;
            checkMyHp();
            Thread.sleep(1000);
            if (atk > 100) {
                actions.stepAside(locationCheck);
                findAndKill();
                atk=1;
            }
        }
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
