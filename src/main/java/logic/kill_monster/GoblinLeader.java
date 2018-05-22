package logic.kill_monster;

import main.Prop;

import java.awt.*;

public class GoblinLeader extends Monster {
    public GoblinLeader() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "KillMonsters\\GoblinLeader\\";
    }
}
