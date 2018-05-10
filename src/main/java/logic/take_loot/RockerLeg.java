package logic.take_loot;

import java.awt.*;

public class RockerLeg extends  Loot{
    public RockerLeg() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "Loot\\RockerLeg\\";
    }
}
