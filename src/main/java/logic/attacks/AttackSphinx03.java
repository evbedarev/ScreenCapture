package logic.attacks;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

//-329224
//-3553057
public class AttackSphinx03 extends Attack {

    public AtomicInteger ATTACK_TIMER = new AtomicInteger(0);

    public AttackSphinx03() throws AWTException {
        rgbParameterList.add(new RgbParameter(-395016,
                new int[] {40,40},
                new int[] {-395016}));

        rgbParameterList.add(new RgbParameter(-460808,
                new int[] {40,40},
                new int[] {-329480}));

    }
}
