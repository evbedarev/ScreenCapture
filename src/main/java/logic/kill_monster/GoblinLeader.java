package logic.kill_monster;
import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class GoblinLeader extends Monster {
    public GoblinLeader(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-6655638,
                new int[] {150,150},
                new int[] {-9878451, -10732731, -9754575}));
    }
}
