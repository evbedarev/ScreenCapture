package logic.kill_monster;

import org.apache.log4j.Logger;
import java.awt.*;

public class Bigfoot extends Monster {
    public Bigfoot(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -4490883;
        super.subImageSize = new int[] {100,100};
        super.ancillaryRgb = new int[] {-4490884, -4097664};
    }
}
