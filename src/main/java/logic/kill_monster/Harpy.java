package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Harpy extends Monster {
    public Harpy(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-2372,
                new int[] {120,120},
                new int[] {-133957,-11617,-545380}));
    }
}
