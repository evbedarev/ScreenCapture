package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class HarpyTalon extends Loot {
    public HarpyTalon(Logger logger) throws AWTException {
        this.logger = logger;
        lootAround = new LootAround(logger);
        rgbParameterList.add(new RgbParameter(-2065,
                new int[] {35,35},
                new int[] {-5403508, -7576725}));

    }
}
