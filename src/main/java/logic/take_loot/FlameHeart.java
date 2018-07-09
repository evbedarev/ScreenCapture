package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class FlameHeart extends Loot {
    public FlameHeart(Logger logger) throws AWTException {
        this.logger = logger;
        lootAround = new LootAround(logger);
        rgbParameterList.add(new RgbParameter(-14932707,
                new int[] {35,35},
                new int[] {-14866914, -3114350}));

    }
}
