package logic;

import checks.CheckHP;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.kill_monster.*;

import logic.take_loot.*;
import java.awt.*;
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
                Thread.sleep(1500);
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
//        KillMonster attack = new Attack();
        count = 0;
        for (int i=1; i < 3; i++) {
            checkMyHp();
            Thread.sleep(1000);
        }
//        Thread.sleep(4000);
        mouse.mouseClick(1000, 450);
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

}

