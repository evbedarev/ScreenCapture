package logic.kill_monster;

import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;


public class Mark1 extends Monster {
    public Mark1(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -6530461;
        super.subImageSize = new int[] {140,140};
        super.ancillaryRgb = new int[] {-8370603};
    }
}
