package logic;

import logic.kill_monster.KillMonster;
import logic.kill_monster.KillMonsterNovice;

import java.awt.*;
import java.io.IOException;

public class LogicForNovice {

    public void start() throws
            IOException,
            AWTException,
            InterruptedException {

        KillMonster killMonster = new KillMonsterNovice();
        while (true) {
            if (killMonster.findAndKill()) {
                Thread.sleep(30000);
            } else {
                Thread.sleep(1000);
            }
        }
    }

}

