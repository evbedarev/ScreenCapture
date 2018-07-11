package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Clothes extends Loot {
    public Clothes(Logger logger, LootAround lootAround) throws AWTException {
        this.logger = logger;
        this.lootAround = lootAround;
        rgbParameterList.add(new RgbParameter(-7245729,
                new int[] {100,100},
                new int[] {-6061455,-7508901}));
    }
}

