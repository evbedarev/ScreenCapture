import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, AWTException, InterruptedException {
        Capture capture = new Capture("somefile");
        BufferedImage image = capture.loadImageFromFile("./src/main/resources/" + "somefile.jpg");
        Thread.currentThread().sleep(1000);
        BufferedImage fragment = capture.loadImageFromFile("./src/main/resources/" + "fragment.png");
        findImage(image, fragment);
        takeScreenShot();
    }

    private static void takeScreenShot() throws IOException, AWTException {
        Capture capture = new Capture("somefile");
        capture.takeScreenShot();
    }

    private static void findImage(BufferedImage screenShot, BufferedImage fragment) throws AWTException {
        PressAndMove move = new PressAndMove();
        Long begin = System.currentTimeMillis();
        FindImageHard findImageHard = new FindImageHard();
        int [] xy = findImageHard.findImage(screenShot, fragment);
        if (xy.length > 1) {
            int x = xy[0];
            int y = xy[1];
            System.out.println("Found! y = " + y + " x = " + x +
                    " take time = " + (System.currentTimeMillis() - begin) + "ms");
            move.moveMouse(x + 18, y + 20);
        } else {
            System.out.println("nothing found");
        }
    }
}
