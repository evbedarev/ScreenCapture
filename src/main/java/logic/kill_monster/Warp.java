package logic.kill_monster;

import java.awt.*;

public class Warp extends Monster {
    public Warp() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "objects\\warp\\";
    }
}
