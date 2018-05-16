package logic;

import checks.CheckHP;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.kill_monster.*;

import logic.take_loot.*;
import java.awt.event.KeyEvent;

public class LogicForNovice {
    int count = 0;
    Mouse mouse;
    CheckHP checkHP;

    public LogicForNovice() throws Exception {
        mouse = new Mouse();
        checkHP = new CheckHP();
    }

    public void start() throws
            Exception {

        Keys keys = new Keys();
//      KillMonster killMonster = new Poring();
        KillMonster killMonster = new ThiefBug();
        KillMonster killMonster1 = new Creamy();
        while (true) {
            if (killMonster.findAndKill() || killMonster1.findAndKill()) {
                Thread.sleep(1000);
                duringTheFight();
            } else {
                count++;
                Thread.sleep(500);
                pickUpLoot();
            }
            if (count > 2) {
                keys.keyPress(KeyEvent.VK_F2);
                Thread.sleep(1000);
                keys.keyPress(KeyEvent.VK_ENTER);
                count = 1;
            }
        }
    }
    private void checkMyHp() throws Exception {
        checkHP.checkHp();
        pickUpCard();
        Thread.sleep(1000);
    }

    private void duringTheFight() throws Exception {
        KillMonster attack = new Attack();
        int atk = 1;
        count = 0;
        while (((Attack) attack).killOrNot()) {
            atk++;
            checkMyHp();
            Thread.sleep(1000);
            if (atk > 20) {
                break;
            }
        }
        stepAside();
        checkHP.needHeal();
        pickUpLoot();
    }

    private void pickUpLoot() throws Exception {
        TakeLoot powderOfButterfly = new PowderOfButterfly();
        TakeLoot honey = new Honey();
        
        pickUpCard();
        powderOfButterfly.pickUp();
        honey.pickUp();
    }

    private void pickUpCard() throws Exception {
        TakeLoot card = new Card();
        TakeLoot clothes = new Clothes();
        card.pickUp();
        clothes.pickUp();
    }

    private void stepAside() throws Exception{
        double t = 2 * Math.PI * Math.random();
        double minRadius = 75;
        double maxRadius = 150;

        double x = minRadius * Math.cos(t);
        double y = minRadius * Math.sin(t);

        double x1 = maxRadius * Math.cos(t);
        double y1 = maxRadius * Math.sin(t);

        double mediumX = x + Math.random()*(x1 - x);
        double mediumR = mediumX/Math.cos(t);
        double mediumY = mediumR * Math.sin(t);

        mouse.mouseClick(800 + (int) Math.round(mediumX),
                450 + (int) Math.round(mediumY));
    }

}

