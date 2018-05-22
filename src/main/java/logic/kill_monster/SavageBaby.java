package logic.kill_monster;

import main.Prop;

import java.awt.*;

public class SavageBaby extends Monster {
    public SavageBaby() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "KillMonsters\\SavageBaby\\";
    }
}
