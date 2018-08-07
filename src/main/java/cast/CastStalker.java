package cast;

import actions.SleepTime;
import checks.location.CheckOverweight;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CastStalker extends Cast {
    private final int threadId;

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
            if (REJECT_SWORD.get() > 30) {
                actions.useRejectSword();
                REJECT_SWORD.set(0);
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
                }
                actions.teleport(Prop.locationCheck);
                TIMER_CHECK_OVERWEIGHT.set(0);
            }
            if(countPutToKafra > 2) {
                keys.keyPress(KeyEvent.VK_F7);
                SleepTime.sleep(5000);
                afterDeathYun11.startActions();
                countPutToKafra = 0;
            }
        }
    }

    @Override
    public void incrementValues() throws InterruptedException {
        incrementConst();
        STEAL_ZENY.incrementAndGet();
        REJECT_SWORD.incrementAndGet();
        sleep(1000);
    }
}
