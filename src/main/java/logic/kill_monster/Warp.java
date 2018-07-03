package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;
//-2305,-1793
public class Warp extends Monster {
    public Warp(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-5331258,
                new int[] {100,100},
                new int[] {-16514044, -16119285, -4870195}));
    }
}
