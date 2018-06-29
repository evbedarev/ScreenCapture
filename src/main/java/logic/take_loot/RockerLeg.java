package logic.take_loot;

import main.Prop;

import java.awt.*;

public class RockerLeg extends  Loot{
    public RockerLeg() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.ROOT_DIR + "Loot\\RockerLeg\\";
        super.lootAround = new LootAround(logger);
    }
}
