package key_and_mouse;

import actions.SleepTime;
import logger.LoggerSingle;

import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse {
    private static volatile Mouse instance;
    private long countMouseClick = 0;
    Robot robot;

    private Mouse() throws AWTException {
        robot = new Robot();
    }

    public static Mouse getInstance() throws AWTException {
        if (instance == null) {
            synchronized (Mouse.class) {
                if (instance == null)
                    instance = new Mouse();
            }
        }
        return instance;
    }

    public synchronized void mouseClick(int x, int y)
            throws AWTException, InterruptedException {
        mouseClick(x, y, InputEvent.BUTTON1_MASK, 100);
    }

    public void mouseClick(int x, int y, int button_mask, int sleepTime)
            throws AWTException, InterruptedException {
        countMouseClick++;
        robot.mouseMove(x, y);
        robot.mousePress(button_mask);
        SleepTime.sleep(sleepTime);
        robot.mouseRelease(button_mask);
//        LoggerSingle.logInfo(this.toString(),("Count mouse click = " + countMouseClick));
    }

    public void leftClick() throws InterruptedException {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        SleepTime.sleep(50);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void pressLeft() throws InterruptedException {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        SleepTime.sleep(100);
    }

    public void releaseLeft() throws InterruptedException {
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        SleepTime.sleep(100);
    }

    public void pressRight() throws InterruptedException {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        SleepTime.sleep(100);
    }

    public void releaseRight() throws InterruptedException {
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        SleepTime.sleep(100);
    }


    public void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    }
}
