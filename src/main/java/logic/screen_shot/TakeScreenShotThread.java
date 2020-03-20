package logic.screen_shot;

import main.Prop;
import java.awt.*;

public class TakeScreenShotThread extends Thread{
    private ScreenShots screenShotStack = Prop.context.getBean(ScreenShot.class);
    private Capture capture = Capture.instance();

    public TakeScreenShotThread() throws AWTException {
    }

    public void run() {
        try {
            while (true) {
               screenShotStack.take(capture.takeScreenShot());
               Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
