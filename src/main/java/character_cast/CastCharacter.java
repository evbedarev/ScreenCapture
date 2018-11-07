package character_cast;

import actions.Actions;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class CastCharacter extends Thread {
    static Actions actions;
    Mouse mouse = Mouse.getInstance();
    public Keys keys = Keys.getInstance();
    final static AtomicInteger ATOMIC_BLESS = new AtomicInteger(0);
    final static AtomicInteger ATOMIC_AGI_UP = new AtomicInteger(0);
    final static AtomicInteger ATOMIC_SUFRA = new AtomicInteger(0);


    public static final AtomicInteger TIMER_CHECK_OVERWEIGHT = new AtomicInteger(0);

    public CastCharacter() throws AWTException {
        actions = Actions.instance();
    }

    void incrementConst() {
        ATOMIC_BLESS.incrementAndGet();
        ATOMIC_AGI_UP.incrementAndGet();
        ATOMIC_SUFRA.incrementAndGet();
    }

    public abstract void begin() throws AWTException;
    public abstract void cast() throws Exception;
    public abstract void incrementValues() throws InterruptedException;

}
