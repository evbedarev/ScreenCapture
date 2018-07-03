package logic.kill_monster;

import logic.RgbParameter;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;

public class Phen extends Monster {
    public Phen(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-10200204,
                new int[] {120,120},
                new int[] {-10265997, -10331790}));
    }
}
