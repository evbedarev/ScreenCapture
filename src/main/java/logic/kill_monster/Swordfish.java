package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Swordfish extends Monster {
    public Swordfish(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-6596253,
                new int[] {140,140},
                new int[] {-6596254,-6661790}));
    }
}
