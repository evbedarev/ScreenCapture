package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Creamy extends Monster {
    public Creamy(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-7050364,
                new int[] {100,100},
                new int[] {-6530461,-7050364}));
    }
}
