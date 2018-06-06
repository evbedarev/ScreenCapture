package logic;

import checks.CheckHP;
import checks.GefField11;
import checks.VerifyMap;
import email.MsgLocationChanged;
import email.SendMessage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.find_element.FragmentElement;
import logic.kill_monster.*;
import logic.take_loot.*;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class LogicGefField11 extends Thread implements Logic {
    private int count = 0;
    private int countForSendMsg = 0;
    private final int threadId;
    private final Mouse mouse = new Mouse();
    private final CheckHP checkHP = new CheckHP();
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);
    private Logger logger = Logger.getLogger(this.getClass());

    private static final int GUARD = KeyEvent.VK_F5;
    private static final int REFLECT_SHIELD = KeyEvent.VK_F6;
    private static final int AWAKING_POTION = KeyEvent.VK_F9;
    private static final int DEFENDER = KeyEvent.VK_F7;

    VerifyMap verifyMap;
    SendMessage sendMessage = new SendMessage();
    Keys keys;
    Attack attack;
    Attack2 attack2;
    KillMonster goblin;
    KillMonster awareMonster;

    private final TakeLoot[] usefulLoot = new TakeLoot[] {
            new Card(logger),
            new Card1(logger),
            new Clothes(logger),
            new Shield(logger)
//            new Mask()
    };


    private final TakeLoot[] loot = new TakeLoot[] {
            new Honey(logger),
            new Scell(logger)
//                new AntelopeSkin(logger),
//                new BlueHerb(logger)
//                new Bottle(logger)
    };


    public LogicGefField11(int threadId) throws Exception {
        verifyMap =  new GefField11();
        goblin = new Goblin(logger);
        awareMonster = new GoblinLeader(logger);
        keys = new Keys();
        attack = new Attack(logger);
        attack2 = new Attack2(logger);
        this.threadId = threadId;
        System.out.println(threadId);
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
            locationCheck();
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
            keys.keyPress(GUARD);
            sleep(2000);
            keys.keyPress(REFLECT_SHIELD);
            ATOMIC_GUARD.set(0);
        }

        if (ATOMIC_AWAKENING.get() > 1800) {
            keys.keyPress(AWAKING_POTION);
            sleep(1000);
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
            keys.keyPress(KeyEvent.VK_F2);
            Thread.sleep(1000);
            keys.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
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
            keys.keyPress(KeyEvent.VK_F2);
            Thread.sleep(1000);
            keys.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
            stepAside();
        }
    }

    void findWaprPortal() throws Exception {
        KillMonster goToWarp = new Warp(logger);
        double t = Math.PI/6;
        double radius = 125;
        int i = 0;
        int x;
        int y;
        while (!goToWarp.kill()) {
            i++;
            sleep(200);
            if (i%20 == 0) {
                t += Math.PI/6;
                x = (int) Math.round(radius * Math.cos(t));
                y = (int) Math.round(radius * Math.sin(t));
                mouse.mouseMove(800 + x, 450 + y);
            }
            if (verifyMap.onDesiredLocation())
                break;
        }
    }

    void locationCheck() throws Exception {
        while (!verifyMap.onDesiredLocation()) {
            sleep(5000);
            logger.info("Нахожусь не на карте!!");
            findWaprPortal();
            sleep(2000);
            if (verifyMap.onDesiredLocation()) {
                teleport();
                sleep(2000);
            }
            countForSendMsg++;
            if (countForSendMsg == 100) {
                sendMessage.send(new MsgLocationChanged());
            }
        }
        countForSendMsg = 0;
    }
}
