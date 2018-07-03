package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Smokie extends Monster {
    public Smokie(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-3693163,
                new int[] {100,100},
                new int[] {-3431016, -7243921}));
    }
}
