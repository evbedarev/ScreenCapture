package cast;

import actions.InterfaceActions;
import actions.SleepTime;
import checks.LocationCheck;
import checks.location.CheckOverweight;
import find_image.FindFragmentInImage;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class CastStalker extends Cast {
    private final int threadId;
    final static AtomicInteger SHADOW_SPELL = new AtomicInteger(0);
    FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();

    public CastStalker(int threadId) throws AWTException {
        this.threadId = threadId;
    }

    @Override
    public void begin() throws AWTException {
        Thread thread = new CastStalker(1);
        thread.start();
    }

    @Override
    public void run() {
        try {
            if (threadId ==  1) {
                while (true) {
                    incrementValues();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void cast() throws Exception {
        if (threadId == 0) {
            if (ATOMIC_AWAKENING.get() > 1800) {
                actions.drinkAwaikeningPotion();
                ATOMIC_AWAKENING.set(0);
            }
            if (PRESERVE.get() > 6 * 60) {
                actions.usePreserve();
                PRESERVE.set(0);
            }
            if (TIMER_CHECK_OVERWEIGHT.get() > Prop.TIMER_CHECK_OVERWEIGHT) {
                LoggerSingle.logInfo(this.toString(), "Cheking overweight");
                if (CheckOverweight.check()) {
                    countPutToKafra++;
                    LoggerSingle.logInfo(this.toString(), "Dropping greenHerb");
                    actions.dropItem(Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                            Prop.ROOT_DIR + "Loot\\GreenHerb\\");
                    LoggerSingle.logInfo(this.toString(), "Dropping YellowHerb");
                    actions.dropItem(Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                            Prop.ROOT_DIR + "Loot\\YellowHerb\\");
                    actions.teleport(Prop.context.getBean("locationCheck", LocationCheck.class));
                }
                TIMER_CHECK_OVERWEIGHT.set(0);
            }
            if(countPutToKafra > 30) {
                keys.keyPress(KeyEvent.VK_F7);
                SleepTime.sleep(5000);
                afterDeathYun11.startActions();
                countPutToKafra = 0;
            }

            if (SHADOW_SPELL.get() > 80) {
                InterfaceActions interfaceActions = InterfaceActions.getInstance();
                keys.keyPress(KeyEvent.VK_F5);
                SleepTime.sleep(5000);
                useSpellShadow();
                SleepTime.sleep(1000);
                interfaceActions.pressOk();
                SleepTime.sleep(1000);
                interfaceActions.pressOk();
                SHADOW_SPELL.set(0);

            }
        }
    }

    @Override
    public void incrementValues() throws InterruptedException {
        incrementConst();
//        STEAL_ZENY.incrementAndGet();
        PRESERVE.incrementAndGet();
        SHADOW_SPELL.incrementAndGet();
        sleep(1000);
    }

    private boolean useSpellShadow() throws Exception {
        Mouse mouse = Mouse.getInstance();
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{600, 1000, 350, 700});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "ShadowSpell\\");
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
            return true;
        }
        return false;
    }

}
