package logic.kill_monster;

import logic.RgbParameter;

import java.awt.*;

public class Horn extends Monster {
    public Horn() throws AWTException {
        rgbParameterList.add(new RgbParameter(-12507594,
                new int[] {20,20},
                new int[] {-12770508, -12243141}));
        super.timeToKillMonster = 1000;
    }
}
