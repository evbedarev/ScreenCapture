package logic.kill_monster;

import java.awt.*;

public class Goblin extends Monster {
    boolean steal;

    public Goblin() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "KillMonsters\\Goblin\\";
    }
}
