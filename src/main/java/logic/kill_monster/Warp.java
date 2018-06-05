package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;
//-2305,-1793
public class Warp extends Monster {
    public Warp(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -5331258;
        super.subImageSize = new int[] {100,100};
        super.ancillaryRgb = new int[] {-16514044, -16119285, -4870195};
    }
}
