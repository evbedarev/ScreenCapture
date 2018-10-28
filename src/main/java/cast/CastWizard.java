package cast;

import actions.SleepTime;
import checks.location.CheckOverweight;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;

public class CastWizard extends Cast {
    private final int threadId;
    private int randomTime;

    public CastWizard(int threadId) throws AWTException {
        this.threadId = threadId;
    }

    @Override
    public void begin() throws AWTException {
        randomTime = 60 + (30 * (int) Math.random());
        Thread thread = new CastWizard(1);
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

            if (ATOMIC_AWAKENING.get() > (60 * randomTime)) {
                SleepTime.sleep(10 * 60 * 1000);
                randomTime = 60 + (int) (30 * Math.random());
                ATOMIC_AWAKENING.set(0);
            }

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
        sleep(1000);
    }
}
