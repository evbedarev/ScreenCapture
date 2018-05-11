package logic.take_loot;

import java.awt.*;

public class PowderOfButterfly extends Loot {
    public PowderOfButterfly() throws AWTException {
            super();
            super.wildcard = "fragm*";
            super.rootDir = settings.rootDir + "Loot\\PowderOfButterfly\\";
    }
}
