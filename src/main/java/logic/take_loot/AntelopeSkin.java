package logic.take_loot;

import main.Prop;

import java.awt.*;

public class AntelopeSkin extends Loot {
    public AntelopeSkin() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\AntelopeSkin\\";
    }
}
