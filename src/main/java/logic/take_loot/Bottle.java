package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Bottle extends Loot {

    public Bottle(Logger logger) throws AWTException {
        this.logger = logger;
        lootAround = new LootAround(logger);
        rgbParameterList.add(new RgbParameter(-8618875,
                new int[] {20,25},
                new int[] {-8618875}));
    }
}
