package logic.take_loot;

import java.awt.*;

public class Shield extends Loot {
    public Shield() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "Loot\\Shield\\";
    }
}
