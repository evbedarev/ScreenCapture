package main;

import logic.LogicForNovice;
import logic.kill_monster.KillMonster;

import java.awt.*;
import java.io.IOException;

public class start {

    public static void main(String[] args) throws
            IOException,
            AWTException,
            InterruptedException {

        LogicForNovice logicForNovice = new LogicForNovice();
        logicForNovice.start();
    }
}
