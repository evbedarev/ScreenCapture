package logic.take_loot;

import main.Prop;

import java.awt.*;

public class Mask extends Loot {
    public Mask() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\Mask\\";
    }
}
