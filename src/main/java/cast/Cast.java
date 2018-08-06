package cast;

import actions.Actions;
import checks.CheckDie;
import checks.LocationCheck;
import checks.afterDeath.AfterDeathYun11;
import checks.location.CheckOverweight;
import key_and_mouse.Keys;
import logger.LoggerSingle;
import main.Prop;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Cast extends Thread {
    public static int countPutToKafra = 0;
    static Actions actions;
    AfterDeathYun11 afterDeathYun11 = new AfterDeathYun11();
    Keys keys = Keys.getInstance();
    static LocationCheck locationCheck;
    final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);
    final static AtomicInteger STEAL_ZENY = new AtomicInteger(0);
    final static AtomicInteger BASH = new AtomicInteger(0);
    public static final AtomicInteger TIMER_CHECK_OVERWEIGHT = new AtomicInteger(0);

    public Cast() throws AWTException {
        actions = Actions.instance();
    }

    void incrementConst() {
        ATOMIC_AWAKENING.incrementAndGet();
        TIMER_CHECK_OVERWEIGHT.incrementAndGet();
    }

    public abstract void begin() throws AWTException;
    public abstract void cast() throws Exception;
    public abstract void incrementValues() throws InterruptedException;

}
