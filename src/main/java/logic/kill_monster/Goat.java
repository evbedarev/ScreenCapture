package logic.kill_monster;

import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;

public class Goat extends Monster {
    public Goat(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "KillMonsters\\Goat\\";
        super.mainRgb = -1523808;
        super.subImageSize = new int[] {100,100};
        super.ancillaryRgb = new int[] {-6062224, -5403528};
    }
}
