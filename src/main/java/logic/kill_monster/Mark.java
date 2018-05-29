package logic.kill_monster;

import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;


public class Mark extends Monster {
    public Mark(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -6530461;
        super.subImageSize = new int[] {140,140};
        super.ancillaryRgb = new int[] {-8567981,-6661790};
    }
}
