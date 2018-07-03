package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class OrcLady extends Monster {
    public OrcLady(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-10857902,
                new int[] {100,100},
                new int[] {-16047357, -9601458}));
    }
}
