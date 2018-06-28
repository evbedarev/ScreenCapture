package logic.take_loot;

import org.apache.log4j.Logger;

import java.awt.*;

public class Card1 extends  Loot {
    public Card1(Logger logger) throws AWTException {
        super.logger = logger;
        super.mainRgb = -6707798;
        super.subImageSize = new int[] {50,50};
        super.ancillaryRgb = new int[] {-8418418, -4999009};
        super.lootAround = new LootAround(logger);
    }
}
