package checks.check_hp;

import actions.SleepTime;
import checks.LocationCheck;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;

public class CheckHpPaladin extends CheckHpByClass {

    public CheckHpPaladin(LocationCheck locationCheck) throws AWTException {
        super(locationCheck);
    }

    @Override
    public void checkHp() throws Exception {
        checkSilenceStatus();
        BufferedImage image = capture.takeScreenShot();

        if (checkHptoRun(image)) {
            date = new Date();
            storageImageFile.save(image, Prop.ROOT_DIR + dateFormat.format(date) + ".png");
            locationCheck.locationCheck();
            checkSilenceStatus();
            actions.teleport();
            LoggerSingle.logInfo(this.getClass().toString(), " Too little HP, run away.");
            while (checkHpToEndRun(image)) {
                if (checkDie.check()) {
                    break;
                }
                locationCheck.locationCheck();
//                checkSilenceStatus();
                if (checkAgressorIsNear.check()) {
                    actions.teleport();
                    SleepTime.sleep(1000);
                }
                SleepTime.sleep(1000);
                actions.heal();
                image = capture.takeScreenShot();
            }
//
//                        while (checkHpToEnd   Run(image)) {
//                if (checkDie.check()) {
//                    break;
//                }
//                locationCheck.locationCheck();
//                actions.teleport();
//                SleepTime.sleep(2000);
//                actions.heal();
//                SleepTime.sleep(1000);
//                checkSP.regenSP();
//                image = capture.takeScreenShot();
//            }
        }
        if (Prop.NEED_HEAL) {
            needHeal();
        }
//        if (needPotion(image)) {
//            keys.keyPress(KeyEvent.VK_F1);
//        }
    }
}
