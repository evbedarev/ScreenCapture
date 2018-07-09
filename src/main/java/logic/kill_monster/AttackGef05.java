package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

//-329224
//-3553057
public class AttackGef05 extends Attack {
    public AttackGef05(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-526090,
                new int[] {40,40},
                new int[] {-3552800}));

        rgbParameterList.add(new RgbParameter(-3552800,
                new int[] {40,40},
                new int[] {-3553057}));

        rgbParameterList.add(new RgbParameter(-3552801,
                new int[] {40,40},
                new int[] {-460553}));
    }
}
