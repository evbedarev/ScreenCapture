package logic.take_loot;

import main.Prop;

import java.awt.*;

public class Bottle extends Loot {

    public Bottle() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\Bottle\\";

        super.mainRgb = -131329;
        super.subImageSize = new int[] {10,16};
        super.ancillaryRgb = new int[] {-7894124, -8618875};
    }
}
