package logic.kill_monster;

import main.Prop;

import java.awt.*;

public class Goblin extends Monster {
    boolean steal;

    public Goblin() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "KillMonsters\\Goblin\\";
    }
}
