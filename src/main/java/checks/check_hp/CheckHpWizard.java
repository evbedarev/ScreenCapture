package checks.check_hp;

import actions.SleepTime;
import checks.LocationCheck;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CheckHpWizard extends CheckHpByClass {

    public CheckHpWizard(LocationCheck locationCheck) throws AWTException {
        super(locationCheck);
    }

    @Override
    public void checkHp() throws Exception {
        checkSilenceStatus();
        BufferedImage image = capture.takeScreenShot();
        if (checkHptoRun(image)) {
            locationCheck.locationCheck();
//            checkSilenceStatus();
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
                } else {
                    SleepTime.sleep(1000);
                    actions.heal();
                }
                image = capture.takeScreenShot();
            }
        }

        if (Prop.NEED_HEAL) {
            if (!checkAgressorIsNear.check())
                needHeal();
        }
//        if (needPotion(image)) {
//            keys.keyPress(KeyEvent.VK_F1);
//        }
    }
}
