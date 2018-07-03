package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Rocker extends Monster {
    public Rocker(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-5386595,
                new int[] {50,50},
                new int[] {-5649765, -4860254}));
    }
}
