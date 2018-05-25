package logic.take_loot;

import main.Prop;

import java.awt.*;

public class BlueHerb extends Loot {
    public BlueHerb() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\BlueHerb\\";

        super.mainRgb = -14083973;
        super.subImageSize = new int[] {10,10};
        super.ancillaryRgb = new int[] {-11451996};
    }
}
