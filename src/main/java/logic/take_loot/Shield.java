package logic.take_loot;

import org.apache.log4j.Logger;

import java.awt.*;

public class Shield extends Loot {
    public Shield(Logger logger) throws AWTException {
        super.logger = logger;
        super.mainRgb = -9868951;
        super.subImageSize = new int[] {50,50};
        super.ancillaryRgb = new int[] {-15461356,-4868683, -7763575};
    }
}
