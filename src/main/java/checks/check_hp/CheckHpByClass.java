package checks.check_hp;

import actions.Actions;
import actions.SleepTime;
import checks.CheckAgressorIsNear;
import checks.CheckSP;
import checks.CheckSilence;
import checks.LocationCheck;
import checks.afterDeath.AfterDeath;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.move_by_card.MoveByCard;
import logic.screen_shot.ScreenShot;
import main.Prop;
import storage_image.StorageImageFile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class CheckHpByClass implements CheckHitPoints {
    Date date;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd'_'hh_mm");
    Keys keys;
    Mouse mouse;
    Actions actions;
    LocationCheck locationCheck;
    AfterDeath checkDie;
    CheckAgressorIsNear checkAgressorIsNear = CheckAgressorIsNear.instance();
    CheckSP checkSP;
    FindPixels findImageHard;
    StorageImageFile storageImageFile = StorageImageFile.instance();
    CheckSilence checkSilence;
    MoveByCard move;

    public CheckHpByClass(LocationCheck locationCheck) throws AWTException {
        this.locationCheck = locationCheck;
        checkDie = Prop.checkDie;
        mouse = Mouse.getInstance();
        keys  = Keys.getInstance();
        actions = Actions.instance();
        checkSP = CheckSP.instance();
        findImageHard = new FindPixels();
        checkSilence = CheckSilence.getInstance();
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
        BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            actions.heal();
        }
    }

    void needHeal(BufferedImage image) throws Exception{
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            actions.heal();
        }
    }

    void checkSilenceStatus() throws InterruptedException {
        if (checkSilence.check()) {
            keys.keyPress(KeyEvent.VK_F4);
            SleepTime.sleep(500);
        }
    }

    @Override
    public abstract void checkHp() throws Exception;

    @Override
    public abstract void checkHp(BufferedImage image) throws Exception;
}
