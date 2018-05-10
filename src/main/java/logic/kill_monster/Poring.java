package logic.kill_monster;

import java.awt.*;

public class Poring extends Monster {
    public Poring() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "KillMonsters\\Poring\\";
    }
}
