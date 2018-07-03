package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Goat extends Monster {
    public Goat(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-7907755,
                new int[] {120,120},
                new int[] {-1194845,-1457759,-2575466, -5272967}));
    }
}
