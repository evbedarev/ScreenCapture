package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class MonstersYun07 extends Monster {
    public MonstersYun07(Logger logger) throws AWTException {
        this.logger = logger;

        //harpy
        rgbParameterList.add(new RgbParameter(-2372,
                new int[] {120,120},
                new int[] {-133957,-11617,-545380}));

        //goat
        rgbParameterList.add(new RgbParameter(-7907755,
                new int[] {120,120},
                new int[] {-1194845,-1457759,-2575466, -5272967}));
    }
}
