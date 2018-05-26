package logic.take_loot;

import org.apache.log4j.Logger;

import java.awt.*;

public class AntelopeSkin extends Loot {
    public AntelopeSkin(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -5208194;
        super.subImageSize = new int[] {40,40};
        super.ancillaryRgb = new int[] {-3761012};
    }
}
