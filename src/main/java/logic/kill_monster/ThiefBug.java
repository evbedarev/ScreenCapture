package logic.kill_monster;

import main.Prop;

import java.awt.*;

public class ThiefBug extends Monster {
    public ThiefBug() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "KillMonsters\\ThiefBug\\";
    }
}
