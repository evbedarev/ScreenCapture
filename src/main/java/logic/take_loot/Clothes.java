package logic.take_loot;

import java.awt.*;

public class Clothes extends Loot{
    public Clothes() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "Loot\\Clothes\\";
    }
}
