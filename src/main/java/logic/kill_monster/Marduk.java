package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Marduk extends Monster {
    public Marduk(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-12901581,
                new int[] {120,120},
                new int[] {-12901582,-11587776,-10995900}));
    }
}
