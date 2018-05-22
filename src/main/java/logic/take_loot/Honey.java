package logic.take_loot;

import main.Prop;

import java.awt.*;

public class Honey extends Loot {
    public Honey() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\Honey\\";
    }
}
