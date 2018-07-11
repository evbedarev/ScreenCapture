package logic.attacks;

import logic.RgbParameter;
import org.apache.log4j.Logger;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

//-329224
//-3553057
public class AttackYun11 extends Attack {

    public AtomicInteger ATTACK_TIMER = new AtomicInteger(0);

    public AttackYun11(Logger logger) throws AWTException {
        this.logger = logger;
//        rgbParameterList.add(new RgbParameter(-3487264,
////                new int[] {40,40},
////                new int[] {-329481}));
////
////        rgbParameterList.add(new RgbParameter(-395017,
////                new int[] {40,40},
////                new int[] {-395017}));
////        rgbParameterList.add(new RgbParameter(-395016,
////                new int[] {40,40},
////                new int[] {-3421470}));
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
