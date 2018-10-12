package cast;

import actions.SleepTime;
import key_and_mouse.Mouse;
import logger.LoggerSingle;

import java.awt.*;
import java.awt.event.KeyEvent;


public class CastMS extends Cast {
    private final int threadId;
    private Mouse mouse;

    public CastMS(int threadId) throws AWTException {
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
            if (ADRENALINE_RUSH.get() > 150) {
                LoggerSingle.logInfo(this.toString(), "cast ADRENALINE_RUSH");
                keys.keyPress(KeyEvent.VK_F4);
                ADRENALINE_RUSH.set(0);
                SleepTime.sleep(1000);
            }

            if (POWER_THRUST.get() > 100) {
                LoggerSingle.logInfo(this.toString(), "cast POWER_THRUST");
                keys.keyPress(KeyEvent.VK_F5);
                POWER_THRUST.set(0);
            }
        }
    }

    @Override
    public void incrementValues() throws InterruptedException {
        incrementConst();
        POWER_THRUST.incrementAndGet();
        ADRENALINE_RUSH.incrementAndGet();
        sleep(1000);
    }
}
