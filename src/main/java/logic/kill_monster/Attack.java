package logic.kill_monster;

import java.awt.*;

public class Attack extends Monster {
    public Attack() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "KillMonsters\\Attack\\";
    }
}
