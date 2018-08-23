package cast;

import checks.location.CheckOverweight;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;

public class CastPaladin extends Cast {
    private final int threadId;

    public CastPaladin(int threadId) throws AWTException {
        this.threadId = threadId;
    }

    @Override
    public void begin() throws AWTException {
        Thread thread = new CastPaladin(1);
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
            if (ATOMIC_GUARD.get() > 300) {
                actions.castGuard();
//                actions.castReflectShield();
                ATOMIC_GUARD.set(0);
            }

            if (ATOMIC_AWAKENING.get() > 1800) {
                actions.drinkAwaikeningPotion();
                ATOMIC_AWAKENING.set(0);
            }

//            if (ATOMIC_DEFENDER.get() > 180) {
//                actions.shieldSpell();
//                sleep(1000);
//                ATOMIC_DEFENDER.set(0);
//            }

//            if (BASH.get() > 10) {
//                actions.useBash();
//                BASH.set(0);
//            }

            if (TIMER_CHECK_OVERWEIGHT.get() > Prop.TIMER_CHECK_OVERWEIGHT) {
                LoggerSingle.logInfo(this.toString(), "Cheking overweight");
                if (CheckOverweight.check()) {
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
        }
    }

    @Override
    public void incrementValues() throws InterruptedException {
        incrementConst();
        ATOMIC_DEFENDER.incrementAndGet();
        ATOMIC_GUARD.incrementAndGet();
//        BASH.incrementAndGet();
        sleep(1000);
    }
}
