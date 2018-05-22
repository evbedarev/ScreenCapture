package logic.kill_monster;

import main.Prop;

import java.awt.*;

public class Warp extends Monster {
    public Warp() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "objects\\warp\\";
    }
}
