import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, AWTException {
        Capture capture = new Capture("somefile");
        BufferedImage image = capture.loadImageFromFile("./src/main/resources/" + "somefile.jpg");
        BufferedImage fragment = capture.loadImageFromFile("./src/main/resources/" + "fragment.png");
//        capture.saveImageToFile(image, "./src/main/resources/" + "somefile2.jpg");
        findImage(image, fragment);

//        capture.takeScreenShot();
    }

    private static void findImage(BufferedImage screenShot, BufferedImage fragment) throws AWTException {
        PressAndMove move = new PressAndMove();
        Long begin = System.currentTimeMillis();
        for (int y = 0; y < screenShot.getHeight() - fragment.getHeight(); y++) {
           __columnspan: for (int x=0; x < screenShot.getWidth() - fragment.getWidth(); x++) {
                if (screenShot.getRGB(x, y) != fragment.getRGB(0,0))
                    continue;
                for (int yy = 0; yy < fragment.getHeight(); yy++) {
                    for (int xx = 0; xx < fragment.getWidth(); xx++) {
                        if (screenShot.getRGB(x + xx, y + yy) != fragment.getRGB(xx , yy))
                            continue __columnspan;
                    }
                }
               System.out.println("Found! y = " + y + " x = " + x +
               " take time = " + (System.currentTimeMillis() - begin) + "ms");
                move.moveMouse(x + 30,y + 20);
            }
        }
    }
}
