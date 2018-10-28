package checks.check_hp;

import actions.SleepTime;
import checks.LocationCheck;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHpMS extends CheckHpByClass {

    public CheckHpMS(LocationCheck locationCheck) throws AWTException {
        super(locationCheck);
    }

    @Override
    public void checkHp() throws Exception {
        if (checkDie.check()) {
            while (true) {
                SleepTime.sleep(5000);
            }
        }

//        BufferedImage image = capture.takeScreenShot();
//        if (checkHptoRun(image)) {
//            date = new Date();
//            storageImageFile.save(image, Prop.ROOT_DIR + dateFormat.format(date) + ".png");
//
////            locationCheck.locationCheck();
//            actions.useWing();
//            LoggerSingle.logInfo(this.getClass().toString(), " Too little HP, run away.");
//        }

        if (Prop.NEED_HEAL) {
            needHeal();
        }
//        if (needPotion(image)) {
//            keys.keyPress(KeyEvent.VK_F1);
//        }
    }

    @Override
    void needHeal(BufferedImage image) throws Exception {
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            keys.keyPress(KeyEvent.VK_F3);
        }
    }

    @Override
    void needHeal() throws Exception{
        BufferedImage image = capture.takeScreenShot();
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            keys.keyPress(KeyEvent.VK_F3);
        }
    }

    public void checkHp(BufferedImage image) throws Exception {
//        BufferedImage image = capture.takeScreenShot();
//        if (checkHptoRun(image)) {
//            date = new Date();
//            storageImageFile.save(image, Prop.ROOT_DIR + dateFormat.format(date) + ".png");
//
////            locationCheck.locationCheck();
//            actions.useWing();
//            LoggerSingle.logInfo(this.getClass().toString(), " Too little HP, run away.");
//        }

        if (Prop.NEED_HEAL) {
            needHeal(image);
        }
//        if (needPotion(image)) {
//            keys.keyPress(KeyEvent.VK_F1);
//        }
    }
}
