package logic.kill_monster;

import main.Prop;

import java.awt.*;

public class Rocker extends Monster {
    public Rocker() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "KillMonsters\\Rocker\\";
    }
}
