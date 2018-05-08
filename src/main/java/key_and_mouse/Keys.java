package key_and_mouse;

import java.awt.*;

public class Keys {
    Robot robot;

    public Keys() throws AWTException {
        robot = new Robot();
    }

    public void keyPress(int keyMask) throws InterruptedException {
        robot.keyPress(keyMask);
        Thread.sleep(100);
        robot.keyRelease(keyMask);
    }
}
