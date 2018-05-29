package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;

public class Merman extends Monster {
    public Merman(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -7899480;
        super.subImageSize = new int[] {200,200};
        super.ancillaryRgb = new int[] {-7247486, -7965273};
    }
}
