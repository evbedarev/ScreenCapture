package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class BlueHerb extends Loot {
    public BlueHerb(Logger logger) throws AWTException {
        this.logger = logger;
        lootAround = new LootAround(logger);
        rgbParameterList.add(new RgbParameter(-14083973,
                new int[] {35,35},
                new int[] {-14083973}));

    }
}
