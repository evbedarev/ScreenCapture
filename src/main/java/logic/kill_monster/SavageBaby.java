package logic.kill_monster;

import java.awt.*;

public class SavageBaby extends Monster {
    public SavageBaby() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "KillMonsters\\SavageBaby\\";
    }
}
