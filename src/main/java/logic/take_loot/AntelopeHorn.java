package logic.take_loot;

import main.Prop;

import java.awt.*;

public class AntelopeHorn extends Loot {
    public AntelopeHorn() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\AntelopeHorn\\";
    }
}
