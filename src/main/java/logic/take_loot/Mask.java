package logic.take_loot;

import java.awt.*;

public class Mask extends Loot {
    public Mask() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "Loot\\Mask\\";
    }
}
