package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;

public class Smokie extends Monster {
    public Smokie(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -3693163;
        super.subImageSize = new int[] {100,100};
        super.ancillaryRgb = new int[] {-3431016, -7243921};
    }
}
