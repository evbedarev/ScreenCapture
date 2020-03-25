package logic.kill_monster;

import logic.RgbParameter;

import java.awt.*;

public class SeaOtter extends Monster {
    public SeaOtter() throws AWTException {
        rgbParameterList.add(new RgbParameter(-11388590,
                new int[] {100,100},
                new int[] {-3223833, -6219, -7050364}));

        super.timeToKillMonster = 2000;
    }
}
