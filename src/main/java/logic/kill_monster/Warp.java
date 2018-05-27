package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;

public class Warp extends Monster {
    public Warp(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -2305;
        super.subImageSize = new int[] {50,50};
        super.ancillaryRgb = new int[] {-1793};
    }
}
