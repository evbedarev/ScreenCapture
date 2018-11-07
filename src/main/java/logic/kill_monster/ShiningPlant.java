package logic.kill_monster;

import logic.RgbParameter;

import java.awt.*;

public class ShiningPlant extends Monster {
    public ShiningPlant() throws AWTException {
        rgbParameterList.add(new RgbParameter(-16756429,
                new int[] {30,30},
                new int[] {-16757711,-16755659}));

        rgbParameterList.add(new RgbParameter(-11206603 ,
                new int[] {30,30},
                new int[] {-11534285,-10747846}));
    }
}
