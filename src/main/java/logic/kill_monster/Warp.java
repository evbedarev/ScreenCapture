package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;
//-2305,-1793
public class Warp extends Monster {
    public Warp(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -1183233;
        super.subImageSize = new int[] {100,100};
        super.ancillaryRgb = new int[] {-1051905};
    }
}
