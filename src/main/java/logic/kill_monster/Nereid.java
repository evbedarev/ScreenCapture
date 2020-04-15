package logic.kill_monster;

import logic.RgbParameter;

import java.awt.*;

public class Nereid extends Monster {
    public Nereid() throws AWTException {
        rgbParameterList.add(new RgbParameter(-2115,
                new int[] {70,70},
                new int[] {-5201711, -7899480}));
        super.timeToKillMonster = 8000;
    }
}
