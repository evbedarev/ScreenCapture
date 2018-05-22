package logic;

import checks.CheckHP;
import checks.VerifyMap;
import email.MsgLocationChanged;
import email.SendMessage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.kill_monster.Attack;
import logic.kill_monster.KillMonster;
import logic.kill_monster.Warp;
import logic.take_loot.*;

import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class Location extends Thread implements Logic {
    int count = 0;
    int countForSendMsg = 0;
    int threadId;
    final Mouse mouse = new Mouse();
    final CheckHP checkHP = new CheckHP();
    final static AtomicInteger atomicInt = new AtomicInteger(0);
    VerifyMap verifyMap;
    SendMessage sendMessage = new SendMessage();
    Keys keys;
    Attack attack;

    Location(int threadId) throws Exception {
        this.threadId = threadId;
        keys = new Keys();
        attack = new Attack();
    }

    @Override
    public void createThread() throws Exception {
        for (int i=0; i < 2; i++) {
            Thread thread = new Location(i);
            thread.start();
        }
    }

    @Override
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
        System.out.println("qq");
    }

    void checkMyHp() throws Exception {
        checkHP.checkHp();
        pickUpCard();
        Thread.sleep(1000);
    }

    void duringTheFight() throws Exception {
        int atk = 1;
        count = 0;
        while (attack.killOrNot()) {
            atk++;
            checkMyHp();
            Thread.sleep(500);
            if (atk > 20) {
                break;
            }
        }
    }

    void pickUpLoot() throws Exception {
        TakeLoot powderOfButterfly = new PowderOfButterfly();
        TakeLoot honey = new Honey();
        pickUpCard();
        powderOfButterfly.pickUp();
        honey.pickUp();
    }

    void pickUpCard() throws Exception {
        TakeLoot card = new Card();
        TakeLoot clothes = new Clothes();
        TakeLoot shield = new Shield();
        TakeLoot mask = new Mask();
        card.pickUp();
        clothes.pickUp();
        shield.pickUp();
        mask.pickUp();
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
    }

    void teleport() throws InterruptedException {
        keys.keyPress(KeyEvent.VK_F2);
        Thread.sleep(1000);
        keys.keyPress(KeyEvent.VK_ENTER);
        count = 1;
    }

    void locationCheck() throws Exception {
        while (!verifyMap.onDesiredLocation()) {
            sleep(5000);
            KillMonster goToWarp = new Warp();
            System.out.println("Нахожусь не на карте!!");
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
