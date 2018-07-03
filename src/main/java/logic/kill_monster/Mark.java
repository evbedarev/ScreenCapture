package logic.kill_monster;

import logic.RgbParameter;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;


public class Mark extends Monster {
    public Mark(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-6530461,
                new int[] {140,140},
                new int[] {-8567981,-6661790}));

    }
}
