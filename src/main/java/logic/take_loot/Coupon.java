package logic.take_loot;

import org.apache.log4j.Logger;

import java.awt.*;

public class Coupon extends Loot {
    public Coupon(Logger logger) throws AWTException {
        super.logger = logger;
        super.mainRgb = -555942;
        super.subImageSize = new int[] {50,60};
        super.ancillaryRgb = new int[] {-2190204,-14667};
        super.lootAround = new LootAround(logger);
    }
}
