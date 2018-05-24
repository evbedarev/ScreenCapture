package logic.take_loot;

import main.Prop;

import java.awt.*;

public class BlueHerb extends Loot {
    public BlueHerb() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\BlueHerb\\";
    }
}
