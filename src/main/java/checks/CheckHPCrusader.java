package checks;

import actions.Actions;
import actions.SleepTime;
import checks.afterDeath.AfterDeath;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.screen_shot.ScreenShot;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHPCrusader {
    private static volatile CheckHPCrusader instance;
    private boolean checkHp;
    private Keys keys;
    private Mouse mouse;
    private Actions actions;
    private LocationCheck locationCheck;
    private AfterDeath checkDie;

    private CheckHPCrusader() {
    }

    static public CheckHPCrusader instance() {
        if (instance == null) {
            synchronized (CheckHPCrusader.class) {
                if (instance == null) {
                    instance = new CheckHPCrusader();
                }
            }
        }
        return instance;
    }

    public void initialize(boolean checkHp, LocationCheck locationCheck) throws
    AWTException {
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
        checkSilence();
        BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();

        if (checkHptoRun(image)) {
            locationCheck.locationCheck();
            actions.useWing();
            checkSilence();
            LoggerSingle.logInfo(this.getClass().toString(), " Too little HP, run away.");
            while (checkHpToEndRun(image)) {
                if (checkDie.check()) {
                    break;
                }
                locationCheck.locationCheck();
                actions.teleport();
                SleepTime.sleep(2000);
                actions.heal();
                SleepTime.sleep(1000);
                image = Prop.context.getBean(ScreenShot.class).pop();
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
        BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            actions.heal();
        }
    }

    private void checkSilence() throws InterruptedException {
        BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
        if (image.getRGB(790,332) == -1) {
            keys.keyPress(KeyEvent.VK_F4);
        }
    }

}
