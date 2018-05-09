package checks;

import key_and_mouse.Keys;
import logic.Capture;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHP {
    private static final int RGB_HP = -6507025;
    public static final int X_HP = 98;
    public static final int Y_HP = 82;
    Capture capture;
    Keys keys;

    public CheckHP() throws AWTException {
        capture = new Capture();
        keys = new Keys();
    }

    public void checkHp()  throws InterruptedException {
        if (needHeal()) {
            keys.keyPress(KeyEvent.VK_F1);
        }
    }

    private boolean needHeal() {
        BufferedImage image = capture.takeScreenShot();
        return image.getRGB(X_HP,Y_HP) != RGB_HP;
    }
}
