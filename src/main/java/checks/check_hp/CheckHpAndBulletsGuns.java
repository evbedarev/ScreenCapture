package checks.check_hp;

import actions.SleepTime;
import actions.kafra_actions.put_loot.KafraActionsPutLoot;
import actions.kafra_actions.put_loot.PutLoot;
import checks.LocationCheck;
import logger.LoggerSingle;
import logic.move_by_card.MoveByCard;
import logic.screen_shot.ScreenShot;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHpAndBulletsGuns extends CheckHpByClass {
    private final KafraActionsPutLoot putLoot = new PutLoot();

    public CheckHpAndBulletsGuns(LocationCheck locationCheck) throws AWTException {
        super(locationCheck);
    }

    @Override
    public void checkHp() throws Exception {
        if (checkDie.check()) {
            SleepTime.loopSleep();
        }
        if (Prop.NEED_HEAL) {
            needHeal();
        }
    }

    @Override
    void needHeal(BufferedImage image) throws Exception {
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            keys.keyPress(KeyEvent.VK_F3);
        }
    }

    @Override
    void needHeal() throws Exception{
        BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            keys.keyPress(KeyEvent.VK_F3);
        }
    }

    public boolean checkBullets(BufferedImage image) {
        if (image.getRGB(1370,880) == -1) {
            LoggerSingle.logInfo(this.getClass().toString(), "No bullets for shot, sleeping");
            return true;
        }
        return false;
    }

    public boolean haveButWings(BufferedImage screenShot) {
        return screenShot.getRGB(1516, 877) != -1;
    }

    @Override
    public void checkHp(BufferedImage image) throws Exception {
        if (checkDie.check(image)) {
            SleepTime.loopSleep();
        }
        if (checkBullets(image)) {
            if (haveButWings(image)) {
                keys.keyPress(KeyEvent.VK_F7);
                Prop.returnFromField.startAction();
            } else {
                SleepTime.loopSleep();
            }
        }

        if (image.getRGB(Prop.X_HP_TO_RUN,Prop.Y_HP) != Prop.RGB_HP) {
            MoveByCard.wingAway();
        }

        if (Prop.NEED_HEAL) {
            needHeal(image);
        }
    }
}
