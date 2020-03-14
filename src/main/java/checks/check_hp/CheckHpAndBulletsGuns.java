package checks.check_hp;

import actions.SleepTime;
import checks.LocationCheck;
import logger.LoggerSingle;
import logic.move_by_card.MoveByCard;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CheckHpAndBulletsGuns extends CheckHpByClass {

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
        BufferedImage image = capture.takeScreenShot();
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

    @Override
    public void checkHp(BufferedImage image) throws Exception {
        if (checkDie.check(image) || checkBullets(image)) {
            SleepTime.loopSleep();
        }

        if (image.getRGB(Prop.X_HP_TO_RUN,Prop.Y_HP) != Prop.RGB_HP) {
            MoveByCard.wingAway();
        }

        if (Prop.NEED_HEAL) {
            needHeal(image);
        }
    }
}
