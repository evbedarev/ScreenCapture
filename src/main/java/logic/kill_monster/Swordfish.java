package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;

public class Swordfish extends Monster {
    public Swordfish(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -6596253;
        super.subImageSize = new int[] {140,140};
        super.ancillaryRgb = new int[] {-6596254,-6661790};
    }
}
