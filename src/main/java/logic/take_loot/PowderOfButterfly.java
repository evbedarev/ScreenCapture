package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class PowderOfButterfly extends Loot {
    public PowderOfButterfly(Logger logger) throws AWTException {
        this.logger = logger;
        lootAround = new LootAround(logger);
        rgbParameterList.add(new RgbParameter(-931161,
                new int[] {50,50},
                new int[] {-534867,-8105390}));
    }
}
