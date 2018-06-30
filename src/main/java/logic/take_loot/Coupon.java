package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Coupon extends Loot {
    public Coupon(Logger logger) throws AWTException {
        this.logger = logger;
        lootAround = new LootAround(logger);
        rgbParameterList.add(new RgbParameter(-555942,
                new int[] {50,60},
                new int[] {-2190204,-14667}));
    }
}
