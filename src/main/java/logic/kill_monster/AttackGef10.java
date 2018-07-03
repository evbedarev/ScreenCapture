package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

//-329224
//-3553057
public class
AttackGef10 extends Monster {
    public AttackGef10(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-3750179,
                new int[] {40,40},
                new int[] {-3750179}));

        rgbParameterList.add(new RgbParameter(-526346,
                new int[] {40,40},
                new int[] {-592139}));

        rgbParameterList.add(new RgbParameter(-3487263,
                new int[] {40,40},
                new int[] {-460809}));

        rgbParameterList.add(new RgbParameter(-3487265,
                new int[] {40,40},
                new int[] {-3487264}));
    }
}
