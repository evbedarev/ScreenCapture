package checks.check_hp;

import actions.SleepTime;
import checks.LocationCheck;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHpStalker extends CheckHpByClass {

    public CheckHpStalker(LocationCheck locationCheck) throws AWTException {
        super(locationCheck);
    }

    @Override
    public void checkHp() throws Exception {
        BufferedImage image = capture.takeScreenShot();
        checkSilence();
        if (checkHptoRun(image)) {
            locationCheck.locationCheck();
            actions.teleport();
            LoggerSingle.logInfo(this.getClass().toString(), " Too little HP, run away.");
            while (checkHpToEndRun(image)) {
                if (checkDie.check()) {
                    break;
                }
                locationCheck.locationCheck();
                checkSilence();

                while (checkAgressorIsNear.check()) {
                    SleepTime.sleep(3000);
                }
//                if (checkAgressorIsNear.check()) {
//                    actions.teleport();
//                    SleepTime.sleep(2000);
//                }
                SleepTime.sleep(3000);
                actions.heal();
                checkSP.regenSP();
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
}
