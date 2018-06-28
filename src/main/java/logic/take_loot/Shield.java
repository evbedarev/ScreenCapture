package logic.take_loot;

import org.apache.log4j.Logger;

import java.awt.*;

public class Shield extends Loot {
    public Shield(Logger logger) throws AWTException {
        super.logger = logger;
        super.mainRgb = -10855846;
        super.subImageSize = new int[] {50,50};
        super.ancillaryRgb = new int[] {-5395027, -15195863};
        super.lootAround = new LootAround(logger);
    }
}
