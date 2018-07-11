package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;
import java.awt.*;

public class Scell extends Loot {
    public Scell(Logger logger, LootAround lootAround) throws AWTException {
        this.logger = logger;
        this.lootAround = lootAround;
        rgbParameterList.add(new RgbParameter(-4361423,
                new int[] {32,32},
                new int[] {-2712502}));
    }
}
