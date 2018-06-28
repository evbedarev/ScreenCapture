package logic.take_loot;

import org.apache.log4j.Logger;
import java.awt.*;

public class Mask extends Loot {
    public Mask(Logger logger) throws AWTException {
        super.logger = logger;
        super.mainRgb = -931161;
        super.subImageSize = new int[] {50,50};
        super.ancillaryRgb = new int[] {-534867,-8105390};
        super.lootAround = new LootAround(logger);
}
}
