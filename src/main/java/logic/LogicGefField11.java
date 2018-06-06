package logic;

import actions.Actions;
import checks.CheckHP;
import checks.location.GefField11;
import checks.LocationCheck;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.kill_monster.*;
import logic.take_loot.*;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class LogicGefField11 extends Thread implements Logic {
    private int count = 0;
    private final int threadId;
    private final Mouse mouse = new Mouse();
    private final CheckHP checkHP = new CheckHP();
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);
    private Logger logger = Logger.getLogger(this.getClass());

    Keys keys;
    Attack attack;
    Attack2 attack2;
    KillMonster goblin;
    KillMonster awareMonster;
    Actions actions;
    private LocationCheck locationCheck = new LocationCheck(new GefField11(), logger);

    private final TakeLoot[] usefulLoot = new TakeLoot[] {
            new Card(logger),
            new Card1(logger),
            new Clothes(logger),
            new Shield(logger)
    };


    private final TakeLoot[] loot = new TakeLoot[] {
            new Honey(logger),
            new Scell(logger)
    };


    public LogicGefField11(int threadId) throws Exception {
        goblin = new Goblin(logger);
        awareMonster = new GoblinLeader(logger);
        keys = new Keys();
        attack = new Attack(logger);
        attack2 = new Attack2(logger);
        this.threadId = threadId;
        System.out.println(threadId);
        actions = Actions.instance();
    }

    public void createThread() throws Exception {
        Thread thread = new LogicGefField11(1);
        thread.start();
        this.start();
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
            runFromMonster();
            locationCheck.locationCheck();
            if (count == 0)
                stepAside();
            findAndKill();
            checkHP.checkHp();
            pickUpLoot();
            teleport();
            count++;
            logger.debug("Incrase count by 1, count=" + count);
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
        checkHP.checkHp();
        pickUpCard();
    }

    void runFromMonster() throws Exception {
        if (awareMonster.kill()) {
            logger.info("GOBLIN LEADER");
            actions.teleport();
        }
    }

    void findAndKill() throws Exception{
        while (goblin.kill())
        {
            count = 0;
            runFromMonster();
            logger.debug("Set count to " + count);
            checkHP.checkHp();
            Thread.sleep(500);
            duringTheFight();
        }
        if (count == 0)
            stepAside();
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
                stepAside();
                findAndKill();
                atk=1;
            }
        }
    }

    void pickUpLoot() throws Exception {
        pickUpCard();
        for (TakeLoot takeLoot: loot) {
            takeLoot.pickUp();
        }
    }

    void pickUpCard() throws Exception {
        for (TakeLoot takeLoot: usefulLoot) {
            takeLoot.pickUp();
        }
    }


    void stepAside() throws Exception {
        double t = 2 * Math.PI * Math.random();
        double minRadius = 75;
        double maxRadius = 150;

        double x = minRadius * Math.cos(t);
        double x1 = maxRadius * Math.cos(t);

        double mediumX = x + Math.random()*(x1 - x);
        double mediumR = mediumX/Math.cos(t);
        double mediumY = mediumR * Math.sin(t);

        mouse.mouseClick(800 + (int) Math.round(mediumX),
                450 + (int) Math.round(mediumY));
        sleep(1000);
    }

    void teleport() throws Exception {
        runFromMonster();
        if (count > 10) {
            logger.info("TELEPORTING count=" + count);
            count = 0;
            logger.info("Set count to " + count);
            actions.teleport();
            stepAside();
        }
    }
}
