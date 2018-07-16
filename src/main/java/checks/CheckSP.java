package checks;

import actions.Actions;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckSP {
    private static volatile CheckSP instance;
    private Capture capture;

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
        return image.getRGB(Prop.X_SP,Prop.Y_SP) != Prop.SP_RGB;
    }

    public boolean enoghtSP(BufferedImage image) {
        return image.getRGB(Prop.X_SP,Prop.Y_SP) == Prop.SP_RGB;
    }
}
