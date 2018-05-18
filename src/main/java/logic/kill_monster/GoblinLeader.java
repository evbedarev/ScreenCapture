package logic.kill_monster;

import java.awt.*;

public class GoblinLeader extends Monster {
    public GoblinLeader() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "KillMonsters\\GoblinLeader\\";
    }
}
