package logic.take_loot;

        import main.Prop;

        import java.awt.*;

public class Clothes extends Loot {
    public Clothes() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "Loot\\Clothes\\";
    }
}

