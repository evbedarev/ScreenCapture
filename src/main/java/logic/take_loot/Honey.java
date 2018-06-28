package logic.take_loot;

import org.apache.log4j.Logger;

import java.awt.*;

public class Honey extends Loot {
    public Honey(Logger logger) throws AWTException {
        super.logger = logger;
        super.mainRgb = -9745846;
        super.subImageSize = new int[] {50,50};
        super.ancillaryRgb = new int[] {-2177380,-2576481, -2181723};
        super.takeLootArround = new TakeLootArround(logger);
    }
}
