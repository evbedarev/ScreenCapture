package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class HarpyTalon extends Loot {
    public HarpyTalon(Logger logger, LootAround lootAround) throws AWTException {
        this.logger = logger;
        this.lootAround = lootAround;
        rgbParameterList.add(new RgbParameter(-2065,
                new int[] {35,35},
                new int[] {-5403508, -7576725}));

    }
}
