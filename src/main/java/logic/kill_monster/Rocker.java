package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;

public class Rocker extends Monster {
    public Rocker(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -5386595;
        super.subImageSize = new int[] {50,50};
        super.ancillaryRgb = new int[] {-5649765, -4860254};
    }
}
