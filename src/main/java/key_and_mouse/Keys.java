package key_and_mouse;

import java.awt.*;

public class Keys {
    private static volatile Keys instance;
    Robot robot;

    private Keys() throws AWTException {
        robot = new Robot();
    }

    public static Keys getInstance() throws AWTException{
        if (instance == null) {
            synchronized (Keys.class) {
                if (instance == null) {
                    instance = new Keys();
                }
            }
        }
        return instance;
    }

    public synchronized void keyPress(int keyMask) throws InterruptedException {
        robot.keyPress(keyMask);
        Thread.sleep(200);
        robot.keyRelease(keyMask);

    }
}
