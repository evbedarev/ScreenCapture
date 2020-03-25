package logic.kill_monster;

import logic.RgbParameter;

import java.awt.*;

public class Galapago extends Monster {
    public Galapago() throws AWTException {
        rgbParameterList.add(new RgbParameter(-11390654,
                new int[] {100,100},
                new int[] {-14086136, -4356964, -9745846}));

        super.timeToKillMonster = 100;
    }
}
