package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;

public class Mark2 extends  Monster {
    public Mark2(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -6661790;
        super.subImageSize = new int[] {140,140};
        super.ancillaryRgb = new int[] {-6530461};
    }
}
