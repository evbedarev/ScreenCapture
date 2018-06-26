package checks;

import actions.Actions;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import static main.Prop.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHP {

    private static final int RGB_HP = getRgbHp();
    private static final int X_HP = getxHp();
    private static final int Y_HP = getyHp();
    private static final int X_HP_HEAL = getxHpHeal();
    private static final int HP_TO_RUN = getxHpToRun();
    private final boolean CHECK_HP;
    Capture capture;
    Keys keys;
    Mouse mouse;
    Actions actions;

    public CheckHP(boolean checkHP) throws AWTException {
        capture = Capture.instance();
        keys = new Keys();
        mouse = new Mouse();
        actions = Actions.instance();
        CHECK_HP = checkHP;
    }

    public void checkHp() throws Exception {
        if (!CHECK_HP)
            return;

        BufferedImage image = capture.takeScreenShot();
        if (checkHptoRun(image)) {
            actions.teleport();
            Thread.sleep(30000);
        }

        if (getHEAL() != 0) {
            needHeal();
        }

        if (needPotion(image)) {
            keys.keyPress(KeyEvent.VK_F1);
        }
    }

    private boolean needPotion(BufferedImage image) {
        return image.getRGB(X_HP,Y_HP) != RGB_HP;
    }

    private boolean checkHptoRun(BufferedImage image) {
        return image.getRGB(HP_TO_RUN,Y_HP) != RGB_HP;
    }

    private void needHeal() throws Exception{
        BufferedImage image = capture.takeScreenShot();
        if (image.getRGB(X_HP_HEAL,Y_HP) != RGB_HP) {
            keys.keyPress(getHEAL());
            Thread.sleep(500);
            mouse.mouseClick(800, 400);
        }
    }
}
