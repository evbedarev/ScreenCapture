package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Honey extends Loot {
    public Honey(Logger logger) throws AWTException {
        lootAround = new LootAround(logger);
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-9745846,
                new int[] {50,50},
                new int[] {-2177380,-2576481, -2181723}));
    }
}
