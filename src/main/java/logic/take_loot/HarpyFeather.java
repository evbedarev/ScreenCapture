package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class HarpyFeather extends Loot {
    public HarpyFeather(Logger logger, LootAround lootAround) throws AWTException {
        this.logger = logger;
        this.lootAround = lootAround;
        rgbParameterList.add(new RgbParameter(-7041883,
                new int[] {20,20},
                new int[] {-8688756, -7107676,-6505068}));

//        hands.add(new RgbParameter(-7041883,
//                new int[] {35,35},
//                new int[] {-8688756, -6505068, -8491121, -1716829}));

    }
}
