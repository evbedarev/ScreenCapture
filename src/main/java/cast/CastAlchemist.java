package cast;

import actions.InterfaceActions;
import actions.SleepTime;
import key_and_mouse.Mouse;
import logger.LoggerSingle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;


public class CastAlchemist extends Cast {
    private InterfaceActions interfaceActions = InterfaceActions.getInstance();
    private final int threadId;
    private Mouse mouse;
    private int randomTime;
    final static AtomicInteger FEED_HOM = new AtomicInteger(0);

    public CastAlchemist(int threadId) throws AWTException {
        randomTime = 80 + (30 * (int) Math.random());
        this.threadId = threadId;
        mouse = Mouse.getInstance();

    }

    @Override
    public void begin() throws AWTException {
        Thread thread = new CastAlchemist(1);
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

            if (FEED_HOM.get() > 350) {
                LoggerSingle.logInfo(this.toString(), "Feeding homunculus");
                interfaceActions.feedHomOnLocation();
                FEED_HOM.set(0);
                SleepTime.sleep(1000);
            }

            goToCharSelect();
        }
    }

    private void goToCharSelect() throws Exception {
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
    }

    @Override
    public void incrementValues() throws InterruptedException {
        incrementConst();
        FEED_HOM.incrementAndGet();
//        ADRENALINE_RUSH.incrementAndGet();
        sleep(1000);
    }
}
