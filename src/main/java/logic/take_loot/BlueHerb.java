package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class BlueHerb extends Loot {
    public BlueHerb(Logger logger, LootAround lootAround) throws AWTException {
        this.logger = logger;
        this.lootAround = lootAround;
        rgbParameterList.add(new RgbParameter(-14083973,
                new int[] {35,35},
                new int[] {-14083973}));

    }
}
