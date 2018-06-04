package logic.take_loot;

import org.apache.log4j.Logger;

import java.awt.*;

public class Clothes extends Loot {
    public Clothes(Logger logger) throws AWTException {
        super.logger = logger;
        super.mainRgb = -7245729;
        super.subImageSize = new int[] {100,100};
        super.ancillaryRgb = new int[] {-6061455,-7508901};
    }
}

