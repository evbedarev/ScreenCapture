package logic.attacks;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

//-329224
//-3553057
public class
AttackYun07 extends Attack {
    public AttackYun07(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-395017,
                new int[] {40,40},
                new int[] {-460810}));

        rgbParameterList.add(new RgbParameter(-3553057,
                new int[] {40,40},
                new int[] {-3487264}));

        rgbParameterList.add(new RgbParameter(-395017,
                new int[] {40,40},
                new int[] {-3553057}));

//        rgbParameterList.add(new RgbParameter(-516,
//                new int[] {40,40},
//                new int[] {-198151}));
//
//        rgbParameterList.add(new RgbParameter(-131845,

//                new int[] {40,40},
//                new int[] {-263687}));
//
//        rgbParameterList.add(new RgbParameter(-3487264,
//                new int[] {40,40},
//                new int[] {-329481}));
//
//        rgbParameterList.add(new RgbParameter(-395017,
//                new int[] {40,40},
//                new int[] {-395017}));
//        rgbParameterList.add(new RgbParameter(-395016,
//                new int[] {40,40},
//                new int[] {-3421470}));
    }

    @Override
    public boolean kill() throws AWTException, InterruptedException {
        for (int i=0; i < 10; i++) {
            BufferedImage screenShot = capture.takeScreenShot();
            if (findAndKill(screenShot)) return true;
        }
        return false;
    }
}