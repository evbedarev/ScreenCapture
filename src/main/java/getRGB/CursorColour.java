package getRGB;

import actions.SleepTime;
import key_and_mouse.Mouse;
import logic.Capture;

import java.awt.image.BufferedImage;

public class CursorColour {

    public static void main(String[] args) throws Exception {
        Capture capture = Capture.instance();
        Mouse mouse = Mouse.getInstance();
        int[] xy;
        for (int i = 0; i < 10; i++) {
            xy = new int[] {(int)(Math.random()* 1600), (int)(Math.random()* 900)};
            BufferedImage image = capture.takeScreenShot();
            mouse.mouseMove(xy[0], xy[1]);
            SleepTime.sleep(1000);
            System.out.println(image.getRGB(xy[0] + 1, xy[1] + 1));
        }

    }
}
