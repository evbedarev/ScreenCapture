package checks.check_hp;

import actions.SleepTime;
import checks.LocationCheck;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;

public class CheckHpStalker extends CheckHpByClass {

    public CheckHpStalker(LocationCheck locationCheck) throws AWTException {
        super(locationCheck);
    }

    @Override
    public void checkHp() throws Exception {
        BufferedImage image = capture.takeScreenShot();
        checkSilenceStatus();
        if (checkHptoRun(image)) {
            /* TEST */
            date = new Date();
            storageImageFile.save(image, Prop.ROOT_DIR + dateFormat.format(date) + ".png");

            locationCheck.locationCheck();
            actions.useWing();
            LoggerSingle.logInfo(this.getClass().toString(), " Too little HP, run away.");
            while (checkHpToEndRun(image)) {
                if (checkDie.check()) {
                    break;
                }
                locationCheck.locationCheck();
                checkSilenceStatus();

//                while (checkAgressorIsNear.check()) {
//                    SleepTime.sleep(3000);
//                    if (checkDie.check()) break;
//                }
                if (checkAgressorIsNear.check()) {
                    actions.teleport();
                    SleepTime.sleep(1000);
                }
                SleepTime.sleep(1000);
                actions.heal();
//                checkSP.regenSP();
                image = capture.takeScreenShot();
            }
        }
        if (Prop.NEED_HEAL) {
            needHeal();
        }
//        if (needPotion(image)) {
//            keys.keyPress(KeyEvent.VK_F1);
//        }
    }
}
