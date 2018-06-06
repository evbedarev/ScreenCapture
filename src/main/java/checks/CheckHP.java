package checks;

import actions.Actions;
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
    private final int X_HP_HEAL;
    Capture capture;
    Keys keys;
    Mouse mouse;
    Actions actions;

    public CheckHP() throws AWTException {
        capture = Capture.instance();
        keys = new Keys();
        mouse = new Mouse();
        actions = Actions.instance();

        RGB_HP = Prop.getRgbHp();
        X_HP = Prop.getxHp();
        Y_HP = Prop.getyHp();
        X_HP_HEAL = Prop.getxHpHeal();
    }

    public void checkHp() throws Exception {
        BufferedImage image = capture.takeScreenShot();
        if (lessThenHalfHp(image)) {
            actions.teleport();
            Thread.sleep(30000);
        }

        if (needPotion(image)) {
            keys.keyPress(KeyEvent.VK_F1);
        }
    }

    private boolean needPotion(BufferedImage image) {
        return image.getRGB(X_HP,Y_HP) != RGB_HP;
    }

    private boolean lessThenHalfHp(BufferedImage image) {
        return image.getRGB(99,Y_HP) != RGB_HP;
    }

    public void needHeal() throws Exception{
        BufferedImage image = capture.takeScreenShot();
        if (image.getRGB(X_HP_HEAL,Y_HP) != RGB_HP) {
            keys.keyPress(KeyEvent.VK_F1);
//            Thread.sleep(500);
//            mouse.mouseClick(800, 400);
        }
    }
}
