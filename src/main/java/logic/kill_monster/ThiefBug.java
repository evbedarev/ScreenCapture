package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class ThiefBug extends Monster {
    public ThiefBug(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-7050364,
                new int[] {100,100},
                new int[] {-9745846, -9743765}));
    }
}
