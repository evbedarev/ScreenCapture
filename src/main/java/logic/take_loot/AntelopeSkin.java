package logic.take_loot;

import main.Prop;

import java.awt.*;

public class AntelopeSkin extends Loot {
    public AntelopeSkin() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\AntelopeSkin\\";
        super.mainRgb = -3958390;
        super.subImageSize = new int[] {10,10};
        super.ancillaryRgb = new int[] {-3761012, 5208194};
    }
}
