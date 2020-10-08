package cast;

import actions.InterfaceActions;
import actions.SleepTime;
import key_and_mouse.Mouse;
import logger.LoggerSingle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;


public class CastMS extends Cast {
    private final int threadId;
    private Mouse mouse;
    private int randomTime;
    final static AtomicInteger ADRENALINE_RUSH = new AtomicInteger(0);
    final static AtomicInteger POWER_THRUST = new AtomicInteger(0);

    public CastMS(int threadId) throws AWTException {
        randomTime = 80 + (30 * (int) Math.random());
        this.threadId = threadId;
        mouse = Mouse.getInstance();

    }

    @Override
    public void begin() throws AWTException {
        Thread thread = new CastMS(1);
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
            if (WAITING_TIME.get() > (60 * randomTime)) {
                LoggerSingle.logInfo(this.toString(), "Sleeping after " + 60 * randomTime + "hours working" );
                InterfaceActions interfaceActions = InterfaceActions.getInstance();
                if (interfaceActions.goToCharSelect()) {
                    SleepTime.sleep(50 * 60 * 1000);
                    randomTime = 80 + (int) (30 * Math.random());
                    LoggerSingle.logInfo(this.toString(), "Awakening after AFK ");
                    keys.keyPress(KeyEvent.VK_ENTER);
                    SleepTime.sleep(2000);
                    WAITING_TIME.set(0);
                } else {
                    SleepTime.loopSleep();
                }
            }
            if (ATOMIC_AWAKENING.get() > 1800) {
                actions.drinkAwaikeningPotion();
                ATOMIC_AWAKENING.set(0);
            }

            if (ADRENALINE_RUSH.get() > 150) {
                LoggerSingle.logInfo(this.toString(), "cast ADRENALINE_RUSH");
//                keys.keyPress(KeyEvent.VK_F4);
                ADRENALINE_RUSH.set(0);
                SleepTime.sleep(1000);
            }

            if (POWER_THRUST.get() > 100) {
                LoggerSingle.logInfo(this.toString(), "cast POWER_THRUST");
//                keys.keyPress(KeyEvent.VK_F5);
                POWER_THRUST.set(0);
            }
        }
    }

    @Override
    public void incrementValues() throws InterruptedException {
//        incrementConst();
//        POWER_THRUST.incrementAndGet();
//        ADRENALINE_RUSH.incrementAndGet();
        sleep(1000);
    }
}
