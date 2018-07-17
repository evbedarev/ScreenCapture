package checks;

import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CheckDie {
    private Capture capture;
    private Keys keys;
    private Mouse mouse;
    private static volatile CheckDie instance;


    private CheckDie() {
    }

    static public CheckDie instance() {
        if (instance == null) {
            synchronized (CheckDie.class) {
                if (instance == null) {
                    instance = new CheckDie();
                }
            }
        }
        return instance;
    }

    public void initialize() throws AWTException {
        capture = Capture.instance();
        keys = new Keys();
        mouse = new Mouse();
    }

    public boolean check() throws Exception {
        BufferedImage image = capture.takeScreenShot();
        if (image.getRGB(Prop.X_HP_TO_DIE,Prop.Y_HP) != Prop.RGB_HP &&
                image.getRGB(673,589) == -14069092  )  {
            mouse.mouseClick(800,615);
            Thread.sleep(1000);
            mouse.mouseClick(880,515);
            return true;
        }

        return false;
    }
}
