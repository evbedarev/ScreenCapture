package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Orc extends Monster {
    public Orc(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-6310019,
                new int[] {100,100},
                new int[] {-11315395, -12039384}));
    }
}
