package checks;

import actions.Actions;
import checks.afterDeath.AfterDeath;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import main.Prop;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHP {
    private static volatile CheckHP instance;
    private boolean checkHp;
    private Capture capture;
    private Keys keys;
    private Mouse mouse;
    private Actions actions;
    private LocationCheck locationCheck;
    private AfterDeath checkDie;
    private CheckAgressorIsNear checkAgressorIsNear = CheckAgressorIsNear.instance();

    private CheckHP() {
    }

    static public CheckHP instance() {
        if (instance == null) {
            synchronized (CheckHP.class) {
                if (instance == null) {
                    instance = new CheckHP();
                }
            }
        }
        return instance;
    }

    public void initialize(boolean checkHp, LocationCheck locationCheck) throws
    AWTException {
        capture = Capture.instance();
        checkDie = Prop.checkDie;
        mouse = Mouse.getInstance();
        keys  = Keys.getInstance();
        actions = Actions.instance();
        this.checkHp = checkHp;
        this.locationCheck = locationCheck;
    }

    public void checkHp() throws Exception {
        if (!checkHp)
            return;
//        checkSilence();
        BufferedImage image = capture.takeScreenShot();

        if (checkHptoRun(image)) {
            locationCheck.locationCheck();
            actions.useWing();
            LoggerSingle.logInfo(this.getClass().toString(), " Too little HP, run away.");
            while (checkHpToEndRun(image)) {
                if (checkDie.check()) {
                    break;
                }

                locationCheck.locationCheck();
                checkSilence();
                if (checkAgressorIsNear.check()) {
                    actions.teleport();
                    Thread.sleep(2000);
                }
                Thread.sleep(3000);
                image = capture.takeScreenShot();
            }
        }

        if (Prop.NEED_HEAL) {
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

    private boolean checkHpToEndRun(BufferedImage image) {
        return image.getRGB(Prop.X_HP_TO_END_RUN,Prop.Y_HP) != Prop.RGB_HP;
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
