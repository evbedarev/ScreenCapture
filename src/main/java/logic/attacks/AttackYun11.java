package logic.attacks;

import logic.RgbParameter;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

//-329224
//-3553057
public class AttackYun11 extends Attack {

    public AtomicInteger ATTACK_TIMER = new AtomicInteger(0);

    public AttackYun11() throws AWTException {
        rgbParameterList.add(new RgbParameter(-395017,
                new int[] {40,40},
                new int[] {-460810}));

        rgbParameterList.add(new RgbParameter(-395017,
                new int[] {40,40},
                new int[] {-329224}));

        rgbParameterList.add(new RgbParameter(-3553057,
                new int[] {40,40},
                new int[] {-3487264}));

        rgbParameterList.add(new RgbParameter(-395017,
                new int[] {40,40},
                new int[] {-3553057}));

        rgbParameterList.add(new RgbParameter(-525831,
                new int[] {40,40},
                new int[] {-525831}));

    }
}
