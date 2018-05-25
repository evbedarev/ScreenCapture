package logic.take_loot;

import main.Prop;

import java.awt.*;

public class Bottle extends Loot {

    public Bottle() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\Bottle\\";
        super.mainRgb = -8618875;
        super.subImageSize = new int[] {20,25};
        super.ancillaryRgb = new int[] {-8618875};
    }
}
