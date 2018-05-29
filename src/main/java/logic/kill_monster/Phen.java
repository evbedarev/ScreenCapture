package logic.kill_monster;

import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;

public class Phen extends Monster {
    public Phen(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -10200204;
        super.subImageSize = new int[] {120,120};
        super.ancillaryRgb = new int[] {-10265997, -10331790};
    }
}
