package logic.take_loot;

import java.awt.*;

public class Honey extends Loot {
    public Honey() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "Loot\\Honey\\";
    }
}
