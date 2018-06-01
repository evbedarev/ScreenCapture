package logic.kill_monster;

import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;

public class Creamy extends Monster {
    public Creamy(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -7779239;
        super.subImageSize = new int[] {100,100};
        super.ancillaryRgb = new int[] {-6530461, -7050364};
    }
}
