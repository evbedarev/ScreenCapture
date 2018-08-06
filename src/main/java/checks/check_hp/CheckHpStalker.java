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
                    SleepTime.sleep(2000);
                }
                SleepTime.sleep(3000);
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
