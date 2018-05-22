package logic.kill_monster;

import main.Prop;

import java.awt.*;

public class Poring extends Monster {
    public Poring() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "KillMonsters\\Poring\\";
    }
}
