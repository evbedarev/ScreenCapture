package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Merman extends Monster {
    public Merman(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-7899480,
                new int[] {200,200},
                new int[] {-7247486, -7965273}));
    }
}
