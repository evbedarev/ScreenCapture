package logic.kill_monster;

import logic.RgbParameter;

import java.awt.*;

public class SwiftPetite extends Monster {
    public SwiftPetite() throws AWTException {
        rgbParameterList.add(new RgbParameter(-8084092,
                new int[] {20,20},
                new int[] {-7426165, -6505068, -3149647}));
        super.timeToKillMonster = 2000;
    }
}
