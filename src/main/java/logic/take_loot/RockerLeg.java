package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class RockerLeg extends  Loot{
    public RockerLeg(Logger logger, LootAround lootAround) throws AWTException {
        this.logger = logger;
        this.lootAround = lootAround;
        rgbParameterList.add(new RgbParameter(-931161,
                new int[] {50,50},
                new int[] {-534867,-8105390}));
    }
}
