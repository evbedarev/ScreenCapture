package logic.screen_shot;

import main.Prop;

import java.awt.*;

public class TakeScreenShotThread extends Thread{
    private ScreenShotStack screenShotStack = Prop.context.getBean(ScreenShotStack.class);
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
