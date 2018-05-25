package logic;

import checks.CheckHP;
import checks.VerifyMap;
import checks.YunField11;
import email.MsgLocationChanged;
import email.SendMessage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.kill_monster.Attack;
import logic.kill_monster.Goat;
import logic.kill_monster.KillMonster;
import logic.kill_monster.Warp;
import logic.take_loot.*;
import org.apache.log4j.Logger;

import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class LogicYunField11 extends Thread implements Logic {
    int count = 0;
    int countForSendMsg = 0;
    int threadId;
    final Mouse mouse = new Mouse();
    final CheckHP checkHP = new CheckHP();
    final static AtomicInteger atomicInt = new AtomicInteger(0);
    private final Logger logger = Logger.getLogger(this.getClass());

    VerifyMap verifyMap;
    SendMessage sendMessage = new SendMessage();
    Keys keys;
    Attack attack;
    KillMonster killMonster;

    TakeLoot[] usefulLoot = new TakeLoot[] {
            new Card(),
            new Clothes(),
            new Shield(),
            new Mask()
    };

    TakeLoot[] loot;


    public LogicYunField11(int threadId) throws Exception {
        System.out.println(threadId);
        verifyMap =  new YunField11();
        killMonster = new Goat();
        loot = new TakeLoot[] {
//                new AntelopeHorn(),
                new AntelopeSkin(),
                new BlueHerb(),
                new Bottle()
        };

        keys = new Keys();
        attack = new Attack();
    }

    public void createThread() throws Exception {
//        Thread thread = new LogicYunField11(1);
//        thread.start();
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
            locationCheck();
            if (count == 0)
                stepAside();
            findAndKill();
            checkHP.checkHp();
            pickUpLoot();
            teleport();
            count++;
            logger.info("Incrase count by 1, count=" + count);
        }

        if (threadId == 1) {
            atomicInt.incrementAndGet();
            sleep(1000);
        }
    }

    void checkMyHp() throws Exception {
        checkHP.checkHp();
//        pickUpCard();
    }

    void findAndKill() throws Exception{
        while (killMonster.findAndKill()) {
            count = 0;
            logger.info("Set count to " + count);
            Thread.sleep(3000);
            duringTheFight();
        }
    }

    void duringTheFight() throws Exception {
        int atk = 1;
        while (attack.killOrNot()) {
            count = 0;
            logger.info("Set count to " + count);
            atk++;
            checkMyHp();
            Thread.sleep(500);
            if (atk > 20) {
                stepAside();
                findAndKill();
                atk=1;
            }
        }
    }

    void pickUpLoot() throws Exception {
//        pickUpCard();
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
        if (count > 40) {
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

    void locationCheck() throws Exception {
        while (!verifyMap.onDesiredLocation()) {
            sleep(5000);
            KillMonster goToWarp = new Warp();
            logger.info("Нахожусь не на карте!!");
            goToWarp.findAndKill();
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
