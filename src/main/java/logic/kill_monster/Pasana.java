package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Pasana extends Monster {
    public Pasana(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-10475202,
                new int[] {120,120},
                new int[] {-10672836,-6596254,-14735327}));
    }
}
