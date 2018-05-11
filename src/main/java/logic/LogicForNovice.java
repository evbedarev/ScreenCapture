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
                duringTheFight();
            } else {
                count++;
                Thread.sleep(1000);
                pickUpLoot();
            }
            if (count > 6) {
                keys.keyPress(KeyEvent.VK_F2);
                Thread.sleep(1000);
                keys.keyPress(KeyEvent.VK_ENTER);
                count = 3;
            }
        }
    }
    private void checkMyHp(int times) throws Exception {
        for (int i = 0; i < times; i++) {
            checkHP.checkHp();
            pickUpCard();
            Thread.sleep(1000);
        }
    }

    private void duringTheFight() throws Exception{
        count = 0;
        checkMyHp(7);
        mouse.mouseClick(1000,450);
        Thread.sleep(1000);
        checkHP.needHeal();
        pickUpLoot();
    }

    private void pickUpLoot() throws Exception {
        TakeLoot rockerLeg = new PowderOfButterfly();
        pickUpCard();
        rockerLeg.pickUp();
    }

    private void pickUpCard() throws Exception {
        TakeLoot card = new Card();
        TakeLoot clothes = new Clothes();
        card.pickUp();
        clothes.pickUp();
    }

}

