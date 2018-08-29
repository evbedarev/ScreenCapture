package checks.check_hp;

import actions.Actions;
import checks.CheckAgressorIsNear;
import checks.CheckSP;
import checks.LocationCheck;
import checks.afterDeath.AfterDeath;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public abstract class CheckHpByClass implements CheckHitPoints {
    Capture capture;
    Keys keys;
    Mouse mouse;
    Actions actions;
    LocationCheck locationCheck;
    AfterDeath checkDie;
    CheckAgressorIsNear checkAgressorIsNear = CheckAgressorIsNear.instance();
    CheckSP checkSP;
    FindPixels findImageHard;

    public CheckHpByClass(LocationCheck locationCheck) throws AWTException {
        this.locationCheck = locationCheck;
        capture = Capture.instance();
        checkDie = Prop.checkDie;
        mouse = Mouse.getInstance();
        keys  = Keys.getInstance();
        actions = Actions.instance();
        checkSP = CheckSP.instance();
        findImageHard = new FindPixels();
    }

    boolean needPotion(BufferedImage image) {
        return image.getRGB(Prop.X_HP,Prop.Y_HP) != Prop.RGB_HP;
    }

    boolean checkHptoRun(BufferedImage image) {
        return image.getRGB(Prop.X_HP_TO_RUN,Prop.Y_HP) != Prop.RGB_HP;
    }

    boolean checkHpToEndRun(BufferedImage image) {
        return image.getRGB(Prop.X_HP_TO_END_RUN,Prop.Y_HP) != Prop.RGB_HP;
    }

    void needHeal() throws Exception{
        BufferedImage image = capture.takeScreenShot();
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            actions.heal();
        }
    }

    void checkSilence() throws InterruptedException {
        BufferedImage image = capture.takeScreenShot();
        if (image.getRGB(790,395) == -1) {
            keys.keyPress(KeyEvent.VK_F4);
        }
    }

    @Override
    public abstract void checkHp() throws Exception;
}
