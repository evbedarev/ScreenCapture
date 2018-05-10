package logic.take_loot;

import java.awt.*;

public class Bottle extends Loot {

    public Bottle() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "Loot\\Bottle\\";
    }
}
