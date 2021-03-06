package key_and_mouse;

import actions.SleepTime;

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
        SleepTime.sleep(100);
        robot.keyRelease(keyMask);
    }

    public void combinationPress(int keyMask1, int keyMask2) throws InterruptedException {
        robot.keyPress(keyMask1);
        SleepTime.sleep(100);
        robot.keyPress(keyMask2);
        SleepTime.sleep(100);
        robot.keyRelease(keyMask2);
        SleepTime.sleep(100);
        robot.keyRelease(keyMask1);
}
}
