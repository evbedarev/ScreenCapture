package logic.kill_monster;

import java.awt.*;

public class ThiefBug extends Monster {
    public ThiefBug() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "KillMonsters\\ThiefBug\\";
    }
}
