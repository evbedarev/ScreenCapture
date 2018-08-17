package logic.kill_monster;

import logic.RgbParameter;
import java.awt.*;

public class MonstersYun07 extends Monster {
    public MonstersYun07() throws AWTException {
        //harpy
        rgbParameterList.add(new RgbParameter(-2115,
                new int[] {90,90},
                new int[] {-2706028,-12644,-547469,-6530461}));

//        //goat
        rgbParameterList.add(new RgbParameter(-3036015,
                new int[]{120, 120},
                new int[]{-1063259, -3956344, -1260637, -6918812}));
    }
}
