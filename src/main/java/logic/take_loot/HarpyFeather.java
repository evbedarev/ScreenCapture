package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class HarpyFeather extends Loot {
    public HarpyFeather(Logger logger) throws AWTException {
        this.logger = logger;
        lootAround = new LootAround(logger);
        rgbParameterList.add(new RgbParameter(-7041883,
                new int[] {35,35},
                new int[] {-8688756, -6505068}));

    }
}
