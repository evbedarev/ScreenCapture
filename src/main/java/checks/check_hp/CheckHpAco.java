package checks.check_hp;

import actions.SleepTime;
import checks.LocationCheck;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHpAco extends CheckHpByClass {

    public CheckHpAco(LocationCheck locationCheck) throws AWTException {
        super(locationCheck);
    }

    @Override
    public void checkHp() throws Exception {
        //        checkSilenceStatus();
        BufferedImage image = capture.takeScreenShot();
//        if (checkHptoRun(image)) {
//            locationCheck.locationCheck();
//            actions.teleport(locationCheck);
//            LoggerSingle.logInfo(this.getClass().toString(), " Too little HP, run away.");
//            while (checkHpToEndRun(image)) {
//                if (checkDie.check()) {
//                    break;
//                }
//                locationCheck.locationCheck();
//                SleepTime.sleep(2000);
//                actions.heal();
//                SleepTime.sleep(1000);
//                checkSP.regenSP();
//                image = capture.takeScreenShot();
//            }
//        }
        System.out.println("Check HP");
        if (Prop.NEED_HEAL) {
            needHeal();
        }
        if (needPotion(image)) {
            keys.keyPress(KeyEvent.VK_F1);
        }
    }
}
