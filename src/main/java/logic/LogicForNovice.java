package logic;

import checks.CheckHP;
import key_and_mouse.Keys;
import logic.kill_monster.KillMonster;
import logic.kill_monster.Poring;
import logic.kill_monster.Rocker;
import logic.take_loot.Bottle;
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
        TakeLoot bottle = new Bottle();


        while (true) {
            if (killMonster.findAndKill()) {
                count = 0;
                checkMyHp(9);
//                Thread.sleep(3000);
                bottle.takeLoot();
            } else {
                count++;
                Thread.sleep(1000);
                bottle.takeLoot();
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

}

