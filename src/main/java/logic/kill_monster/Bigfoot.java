package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;
import java.awt.*;

public class Bigfoot extends Monster {
    public Bigfoot(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-4490883,
                new int[] {100,100},
                new int[] {-4490884, -4097664}));

    }
}
