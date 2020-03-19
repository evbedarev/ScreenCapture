package logic.screen_shot;

import java.awt.*;

public class TakeScreenShotThread extends Thread{
    private ScreenShotStack screenShotStack = new ScreenShotStack();
    private Capture capture = Capture.instance();

    public TakeScreenShotThread() throws AWTException {
    }

    public void run() {
        try {
            while (true) {
               screenShotStack.push(capture.takeScreenShot());
               Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
