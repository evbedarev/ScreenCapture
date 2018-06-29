package logic.take_loot;

import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;

public class Bottle extends Loot {

    public Bottle(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.wildcard = "fragm*";
        super.rootDir = Prop.ROOT_DIR + "Loot\\Bottle\\";
        super.mainRgb = -8618875;
        super.subImageSize = new int[] {20,25};
        super.ancillaryRgb = new int[] {-8618875};
        super.lootAround = new LootAround(logger);
    }
}
