package logic.take_loot;

import main.Prop;

import java.awt.*;

public class Card extends Loot {
    public Card() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\Card\\";
    }
}
