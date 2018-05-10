package checks;

import key_and_mouse.Keys;
import logic.Capture;
import main.Settings;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHP {
    private final int RGB_HP;
    private final int X_HP;
    private final int Y_HP;
    Capture capture;
    Keys keys;

    public CheckHP() throws AWTException {
        Settings settings = Settings.instance();
        capture = new Capture();
        keys = new Keys();
        RGB_HP = settings.rgbHp;
        X_HP = settings.xHp;
        Y_HP = settings.yHp;
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
