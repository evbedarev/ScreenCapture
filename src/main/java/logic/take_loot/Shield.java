package logic.take_loot;

import main.Prop;

import java.awt.*;

public class Shield extends Loot {
    public Shield() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\Shield\\";
    }
}
