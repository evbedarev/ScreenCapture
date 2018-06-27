package logic.take_loot;

import main.Prop;

import java.awt.*;

public class RockerLeg extends  Loot{
    public RockerLeg() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\RockerLeg\\";
        super.takeLootArround = new TakeLootArround(logger);
    }
}
