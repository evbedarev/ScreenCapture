package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;

public class ThiefBug extends Monster {
    public ThiefBug(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -7050364;
        super.subImageSize = new int[] {100,100};
        super.ancillaryRgb = new int[] {-9745846, -9743765};
    }
}
