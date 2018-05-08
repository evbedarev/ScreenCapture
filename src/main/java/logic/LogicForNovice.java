package logic;

import logic.kill_monster.KillMonster;
import logic.kill_monster.KillMonsterNovice;
import logic.take_loot.Bottle;
import logic.take_loot.TakeLoot;

import java.awt.*;
import java.io.IOException;

public class LogicForNovice {

    public void start() throws
            IOException,
            AWTException,
            InterruptedException {

        KillMonster killMonster = new KillMonsterNovice();
        TakeLoot bottle = new Bottle();

        while (true) {
            if (killMonster.findAndKill()) {
                Thread.sleep(3000);
                bottle.takeLoot();
            } else {
                Thread.sleep(1000);
                bottle.takeLoot();
            }
        }
    }

}

