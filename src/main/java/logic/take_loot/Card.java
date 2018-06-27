package logic.take_loot;

import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;

public class Card extends Loot {
    public Card(Logger logger) throws AWTException {
        super.logger = logger;
        super.mainRgb = -4864314;
        super.subImageSize = new int[] {50,50};
        super.ancillaryRgb = new int[] {-4864314};
        super.takeLootArround = new TakeLootArround(logger);
    }
}
