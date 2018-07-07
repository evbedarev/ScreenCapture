package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

//-329224
//-3553057
public class AttackYun07 extends Monster {
    public AttackYun07(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-516,
                new int[] {40,40},
                new int[] {-198151}));

        rgbParameterList.add(new RgbParameter(-131845,
                new int[] {40,40},
                new int[] {-263687}));

        rgbParameterList.add(new RgbParameter(-3487264,
                new int[] {40,40},
                new int[] {-329481}));

        rgbParameterList.add(new RgbParameter(-395017,
                new int[] {40,40},
                new int[] {-395017}));
        rgbParameterList.add(new RgbParameter(-395016,
                new int[] {40,40},
                new int[] {-3421470}));
    }
}
