package logic.kill_monster;

import org.apache.log4j.Logger;

import java.awt.*;

public class Goblin extends Monster {
    public Goblin(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -7905956;
        super.subImageSize = new int[] {100,100};
        super.ancillaryRgb = new int[] {-2710091, -9680053};
    }
}
