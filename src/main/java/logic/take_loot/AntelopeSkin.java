package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class AntelopeSkin extends Loot {
    public AntelopeSkin(Logger logger, LootAround lootAround) throws AWTException {
        this.logger = logger;
        this.lootAround = lootAround;
        rgbParameterList.add(new RgbParameter(-5208194,
                new int[] {40,40},
                new int[] {-3761012}));
    }
}
