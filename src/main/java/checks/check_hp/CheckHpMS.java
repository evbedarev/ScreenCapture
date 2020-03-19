package checks.check_hp;

import actions.SleepTime;
import checks.LocationCheck;
import logic.move_by_card.MoveByCard;
import logic.screen_shot.ScreenShotStack;
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
            SleepTime.loopSleep();
        }

        if (Prop.NEED_HEAL) {
            needHeal();
        }
    }

    @Override
    public void checkHp(BufferedImage image) throws Exception {
        if (checkDie.check(image)) {
            SleepTime.loopSleep();
        }

        if (image.getRGB(Prop.X_HP_TO_RUN,Prop.Y_HP) != Prop.RGB_HP) {
            MoveByCard.wingAway();
        }

        if (Prop.NEED_HEAL) {
            needHeal(image);
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
        BufferedImage image = Prop.context.getBean(ScreenShotStack.class).pop();
        if (image.getRGB(Prop.X_HP_HEAL,Prop.Y_HP) != Prop.RGB_HP) {
            keys.keyPress(KeyEvent.VK_F3);
        }
    }
}
