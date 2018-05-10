package logic;

import checks.CheckHP;
import key_and_mouse.Keys;
import logic.kill_monster.KillMonster;

import logic.kill_monster.Poring;
import logic.kill_monster.Rocker;
import logic.kill_monster.SavageBaby;
import logic.take_loot.Bottle;
import logic.take_loot.Card;
import logic.take_loot.RockerLeg;
import logic.take_loot.TakeLoot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LogicForNovice {
    int count = 0;
    public void start() throws
            IOException,
            AWTException,
            InterruptedException {

        Keys keys = new Keys();
//      KillMonster killMonster = new Poring();
        KillMonster killMonster = new Rocker();
        KillMonster killMonster1 = new SavageBaby();


        while (true) {
            if (killMonster.findAndKill() || killMonster1.findAndKill()) {
                count = 0;
                checkMyHp(7);
                pickUpLoot();
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

    private void pickUpLoot() throws AWTException,
            IOException,
            InterruptedException {

        TakeLoot rockerLeg = new RockerLeg();
        TakeLoot card = new Card();

        if (card.takeLoot()) {
            Thread.sleep(10000);
        }

        if (rockerLeg.takeLoot()) {
            Thread.sleep(1000);
        }

    }

}

