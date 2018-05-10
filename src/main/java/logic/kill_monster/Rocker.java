package logic.kill_monster;

import java.awt.*;

public class Rocker extends Monster {
    public Rocker() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "KillMonsters\\Rocker\\";
    }
}
