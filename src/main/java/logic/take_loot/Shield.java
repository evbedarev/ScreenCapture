package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Shield extends Loot {
    public Shield(Logger logger) throws AWTException {
        this.logger = logger;
        lootAround = new LootAround(logger);
        rgbParameterList.add(new RgbParameter(-10855846,
                new int[] {50,50},
                new int[] {-5395027, -15195863}));
    }
}
