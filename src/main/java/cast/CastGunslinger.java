package cast;

import actions.InterfaceActions;
import actions.SleepTime;
import key_and_mouse.Mouse;
import logger.LoggerSingle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;


public class CastGunslinger extends Cast {
    private final int threadId;
    private Mouse mouse;
    private int randomTime;
    final static AtomicInteger GATLING_FEVER = new AtomicInteger(0);
    final static AtomicInteger FLIP_THE_COINS = new AtomicInteger(0);
    final static AtomicInteger ADJUSTMENT = new AtomicInteger(0);

    public CastGunslinger(int threadId) throws AWTException {
        randomTime = 80 + (30 * (int) Math.random()); //80 + (30 * (int) Math.random());
        this.threadId = threadId;
        mouse = Mouse.getInstance();

    }

    @Override
    public void begin() throws AWTException {
        Thread thread = new CastGunslinger(1);
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
                    SleepTime.sleep(50 * 60 * 1000);//50
                    randomTime = 80 + (30 * (int) Math.random());
                    LoggerSingle.logInfo(this.toString(), "Awakening after AFK ");
                    keys.keyPress(KeyEvent.VK_ENTER);
                    SleepTime.sleep(2000);
                    mouse.mouseMove(575, 896);
                    SleepTime.sleep(200);
                    mouse.pressLeft();
                    SleepTime.sleep(500);
                    mouse.mouseMove(5, 896);
                    SleepTime.sleep(500);
                    mouse.releaseLeft();
                    WAITING_TIME.set(0);
                } else {
                    SleepTime.loopSleep();
                }
            }

//            if (GATLING_FEVER.get() > 165) {
//                LoggerSingle.logInfo(this.toString(), "cast GATLING_FEVER");
//                keys.keyPress(KeyEvent.VK_F4);
//                SleepTime.sleep(3000);
//                GATLING_FEVER.set(0);
//            }

            if (FLIP_THE_COINS.get() > 85) {
                LoggerSingle.logInfo(this.toString(), "cast FLIP_COINS");
                keys.keyPress(KeyEvent.VK_F6);
//                SleepTime.sleep(1500);
//                keys.keyPress(KeyEvent.VK_F4);
//                SleepTime.sleep(1500);
//                keys.keyPress(KeyEvent.VK_F6);
//                SleepTime.sleep(500);
                FLIP_THE_COINS.set(0);
            }

            if (ADJUSTMENT.get() > 30) {
                LoggerSingle.logInfo(this.toString(), "cast ADJUSTMENT");
                keys.keyPress(KeyEvent.VK_F7);
                SleepTime.sleep(1000);
                ADJUSTMENT.set(0);
            }

//            if (ATOMIC_AWAKENING.get() > 1800) {
//                actions.drinkAwaikeningPotion();
//                ATOMIC_AWAKENING.set(0);
//            }
        }
    }

    @Override
    public void incrementValues() throws InterruptedException {
        incrementConst();
//        GATLING_FEVER.incrementAndGet();
        FLIP_THE_COINS.incrementAndGet();
//        ADJUSTMENT.incrementAndGet();
        sleep(1000);
    }
}
