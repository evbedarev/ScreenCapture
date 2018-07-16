package checks;
import logic.Capture;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CheckSP {
    private static volatile CheckSP instance;
    private Capture capture;
    public static boolean enoughSP;

    private CheckSP() {
    }

    static public CheckSP instance() {
        if (instance == null) {
            synchronized (CheckSP.class) {
                if (instance == null) {
                    instance = new CheckSP();
                }
            }
        }
        return instance;
    }

    public void initialize() throws
    AWTException {
        capture = Capture.instance();
    }

    public boolean enoghtSP() {
        BufferedImage image = capture.takeScreenShot();
        enoughSP = image.getRGB(Prop.X_SP,Prop.Y_SP) != Prop.SP_RGB;
        return enoughSP;
    }

    public boolean enoghtSP(BufferedImage image) {
        enoughSP = image.getRGB(Prop.X_SP,Prop.Y_SP) != Prop.SP_RGB;
        return enoughSP;
    }
}
