package logic.kill_monster;

import main.Prop;

import java.awt.*;

public class Goat extends Monster {
    public Goat() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "KillMonsters\\Goat\\";
    }
}
