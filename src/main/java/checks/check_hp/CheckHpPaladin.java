package checks.check_hp;

import actions.SleepTime;
import checks.LocationCheck;
import logger.LoggerSingle;
import logic.screen_shot.ScreenShotStack;
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
        BufferedImage image = Prop.context.getBean(ScreenShotStack.class).pop();

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
                image = Prop.context.getBean(ScreenShotStack.class).pop();
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
//                image = Prop.context.getBean(ScreenShotStack.class).pop();
//            }
        }
        if (Prop.NEED_HEAL) {
            needHeal();
        }
//        if (needPotion(image)) {
//            keys.keyPress(KeyEvent.VK_F1);
//        }
    }

    @Override
    public void checkHp(BufferedImage image) throws Exception {

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
                if (checkAgressorIsNear.check()) {
                    actions.teleport();
                    SleepTime.sleep(1000);
                }
                SleepTime.sleep(1000);
                actions.heal();
                image = Prop.context.getBean(ScreenShotStack.class).pop();
            }

        }
        if (Prop.NEED_HEAL) {
            needHeal();
        }
    }
}
