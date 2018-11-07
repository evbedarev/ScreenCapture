package cast;

import actions.SleepTime;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import java.awt.*;
import java.awt.event.KeyEvent;


public class CastAco extends Cast {
    private final int threadId;
    private Mouse mouse;

    public CastAco(int threadId) throws AWTException {
        this.threadId = threadId;
        mouse = Mouse.getInstance();

    }

    @Override
    public void begin() throws AWTException {
        Thread thread = new CastAco(1);
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

            if (BLESS.get() > 240) {
                LoggerSingle.logInfo(this.toString(), "cast BLESS");
                keys.keyPress(KeyEvent.VK_F5);
                SleepTime.sleep(500);
                try {
                    mouse.mouseClick(800, 420);
                } catch (AWTException exception) {
                    exception.printStackTrace();
                }
                BLESS.set(0);
                SleepTime.sleep(1000);
            }

            if (AGI_UP.get() > 240) {
                LoggerSingle.logInfo(this.toString(), "cast AGI UP");
                keys.keyPress(KeyEvent.VK_F4);
                SleepTime.sleep(500);
                try {
                    mouse.mouseClick(800, 420);
                } catch (AWTException exception) {
                    exception.printStackTrace();
                }
                AGI_UP.set(0);
            }
        }
    }

    @Override
    public void incrementValues() throws InterruptedException {
        incrementConst();
        AGI_UP.incrementAndGet();
        BLESS.incrementAndGet();
        sleep(1000);
    }
}
