package logic.kill_monster;

import logic.RgbParameter;

import java.awt.*;

public class Mantis extends Monster {
    public Mantis() throws AWTException {
        rgbParameterList.add(new RgbParameter(-9206702,
                new int[] {20,20},
                new int[] {-1067644, -2168923}));
        super.timeToKillMonster = 1000;
    }
}
