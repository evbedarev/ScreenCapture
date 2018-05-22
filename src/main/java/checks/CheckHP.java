package checks;

import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHP {
    private final int RGB_HP;
    private final int X_HP;
    private final int Y_HP;
    private final int RGB_HP_HEAL;
    private final int X_HP_HEAL;
    private final int Y_HP_HEAL;
    Capture capture;
    Keys keys;
    Mouse mouse;

    public CheckHP() throws AWTException {
        capture = Capture.instance();
        keys = new Keys();
        mouse = new Mouse();

        RGB_HP = Prop.getRgbHp();
        X_HP = Prop.getxHp();
        Y_HP = Prop.getyHp();

        RGB_HP_HEAL = Prop.getRgbHpHeal();
        X_HP_HEAL = Prop.getxHp();
        Y_HP_HEAL = Prop.getyHp();
    }

    public void checkHp() throws Exception {
        if (needPotion()) {
            keys.keyPress(KeyEvent.VK_F1);
        }
    }

    private boolean needPotion() {
        BufferedImage image = capture.takeScreenShot();
        return image.getRGB(X_HP,Y_HP) != RGB_HP;
    }

    public void needHeal() throws Exception{
        BufferedImage image = capture.takeScreenShot();
        if (image.getRGB(X_HP_HEAL,Y_HP_HEAL) != RGB_HP_HEAL) {
            keys.keyPress(KeyEvent.VK_F1);
//            Thread.sleep(500);
//            mouse.mouseClick(800, 400);
        }
    }
}
