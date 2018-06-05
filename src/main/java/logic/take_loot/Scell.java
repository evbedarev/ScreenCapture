package logic.take_loot;

import org.apache.log4j.Logger;
import java.awt.*;

public class Scell extends Loot {
    public Scell(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -4361423;
        super.subImageSize = new int[] {32,32};
        super.ancillaryRgb = new int[] {-2712502};
    }
}
