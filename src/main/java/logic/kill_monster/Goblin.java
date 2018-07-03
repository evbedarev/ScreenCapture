package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Goblin extends Monster {
    public Goblin(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-7905956,
                new int[] {100,100},
                new int[] {-2710091, -9680053}));
    }
}
