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
    private final boolean CHECK_HP;
    Capture
            capture;
    Keys keys;
    Mouse mouse;
    Actions actions;
    LocationCheck locationCheck;

    public CheckHP(boolean checkHP, LocationCheck locationCheck)
            throws AWTException {
        capture = Capture.instance();
        keys = new Keys();
        mouse = new Mouse();
        actions = Actions.instance();
        CHECK_HP = checkHP;
        this.locationCheck = locationCheck;
    }

    public void checkHp() throws Exception {
        if (!CHECK_HP)
            return;
        checkSilence();

        BufferedImage image = capture.takeScreenShot();
        if (checkHptoRun(image)) {
            locationCheck.locationCheck();
            actions.useWing();
            while (needPotion(image)) {
                checkSilence();
                actions.teleport();
                Thread.sleep(5000);
                image = capture.takeScreenShot();
            }
        }

        if (Prop.X_HP_HEAL != 0) {
            needHeal();
        }

        if (needPotion(image)) {
            keys.keyPress(KeyEvent.VK_F1);
        }
    }

    private boolean needPotion(BufferedImage image) {
        return image.getRGB(Prop.X_HP,Prop.Y_HP) != Prop.RGB_HP;
    }

    private boolean checkHptoRun(BufferedImage image) {
        return image.getRGB(Prop.X_HP_TO_RUN,Prop.Y_HP) != Prop.RGB_HP;
    }

    private void needHeal() throws Exception{
        BufferedImage image = capture.takeScreenShot();
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            keys.keyPress(Prop.HEAL_KEY);
            Thread.sleep(500);
            mouse.mouseClick(800, 400);
        }
    }

    private void checkSilence() throws InterruptedException {
        BufferedImage image = capture.takeScreenShot();
        if (image.getRGB(790,380) == -1) {
            keys.keyPress(KeyEvent.VK_F4);
        }
    }

}
