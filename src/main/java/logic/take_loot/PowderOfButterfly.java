package logic.take_loot;

import main.Prop;

import java.awt.*;

public class PowderOfButterfly extends Loot {
    public PowderOfButterfly() throws AWTException {
            super();
            super.wildcard = "fragm*";
            super.rootDir = Prop.ROOT_DIR + "Loot\\PowderOfButterfly\\";
        super.lootAround = new LootAround(logger);
    }
}
