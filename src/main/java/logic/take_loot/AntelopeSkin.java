package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class AntelopeSkin extends Loot {
    public AntelopeSkin(Logger logger) throws AWTException {
        this.logger = logger;
        lootAround = new LootAround(logger);
        rgbParameterList.add(new RgbParameter(-5208194,
                new int[] {40,40},
                new int[] {-3761012}));
    }
}
