package logic.kill_monster;
import org.apache.log4j.Logger;

import java.awt.*;

public class GoblinLeader extends Monster {
    public GoblinLeader(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -6655638;
        super.subImageSize = new int[] {150,150};
        super.ancillaryRgb = new int[] {-9878451, -10732731, -9754575};
    }
}
