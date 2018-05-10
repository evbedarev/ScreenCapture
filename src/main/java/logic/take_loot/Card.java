package logic.take_loot;

import java.awt.*;

public class Card extends Loot {
    public Card() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "Loot\\Card\\";
    }
}
