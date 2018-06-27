package logic.take_loot;

import org.apache.log4j.Logger;

import java.awt.*;

public class BlueHerb extends Loot {
    public BlueHerb(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -14083973;
        super.subImageSize = new int[] {35,35};
        super.ancillaryRgb = new int[] {-14083973};
        super.takeLootArround = new TakeLootArround(logger);
    }
}
