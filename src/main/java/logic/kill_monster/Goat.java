package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;

public class Goat extends Monster {
    public Goat(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -7907755;
        super.subImageSize = new int[] {120,120};
        super.ancillaryRgb = new int[] {-4876673, -1852259, -1194845, -2312552
        };
    }
}
