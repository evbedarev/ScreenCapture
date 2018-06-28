package logic.take_loot;

import org.apache.log4j.Logger;

import java.awt.*;

public class Card extends Loot {
    public Card(Logger logger) throws AWTException {
        super.logger = logger;
        super.mainRgb = -4864314;
        super.subImageSize = new int[] {50,50};
        super.ancillaryRgb = new int[] {-4864314};
        super.lootAround = new LootAround(logger);
    }
}
