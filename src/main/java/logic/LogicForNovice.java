package logic;

import checks.CheckHP;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.kill_monster.*;

import logic.take_loot.*;

import java.awt.*;
import java.awt.event.KeyEvent;

import java.io.Externalizable;
import java.io.IOException;

public class LogicForNovice {
    int count = 0;
    Mouse mouse;

    public LogicForNovice() throws Exception {
        mouse = new Mouse();
    }

    public void start() throws
            Exception {

        Keys keys = new Keys();
//      KillMonster killMonster = new Poring();
        KillMonster killMonster = new ThiefBug();
        KillMonster killMonster1 = new Creamy();


        while (true) {
            if (killMonster.findAndKill() || killMonster1.findAndKill()) {
                duringTheFight();
            } else {
                count++;
                Thread.sleep(1000);
                pickUpLoot();
            }
            if (count > 10) {
                keys.keyPress(KeyEvent.VK_F2);
                Thread.sleep(500);
                keys.keyPress(KeyEvent.VK_ENTER);
                count = 5;
            }
        }
    }
    private void checkMyHp(int times) throws AWTException, InterruptedException {
        CheckHP checkHP = new CheckHP();
        for (int i = 0; i < times; i++) {
            checkHP.checkHp();
            Thread.sleep(1000);
        }
    }

    private void duringTheFight() throws Exception{
        count = 0;
        checkMyHp(7);
        mouse.mouseClick(1000,450);
        Thread.sleep(1000);
        pickUpLoot();
    }

    private void pickUpLoot() throws AWTException,
            IOException,
            InterruptedException {

        TakeLoot rockerLeg = new PowderOfButterfly();
        TakeLoot card = new Card();

        if (card.takeLoot()) {
            Thread.sleep(10000);
        }

        if (rockerLeg.takeLoot()) {
            Thread.sleep(2000);
        }

    }

}

