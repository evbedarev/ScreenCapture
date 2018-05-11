package logic.kill_monster;

import java.awt.*;

public class Creamy extends Monster {
    public Creamy() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "KillMonsters\\Creamy\\";
    }
}
