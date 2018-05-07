import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Capture {
    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;
    private static final String ROOT_DIR = "./src/main/resources/";
    private final String fileName;


    public Capture(String filename) {
        this.fileName = filename + ".jpg";
    }

    public void takeScreenShot() throws AWTException,IOException {
        Robot robot = new Robot();
        Rectangle rectangle = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT);
        BufferedImage image = robot.createScreenCapture(rectangle);
        saveImageToFile(image, ROOT_DIR + fileName);
    }

    public void saveImageToFile(BufferedImage image, String fullFilePath) throws IOException {
        File outputfile = new File(fullFilePath);
        ImageIO.write(image,"jpg",outputfile);
    }

    public BufferedImage loadImageFromFile (String fragmentPath) throws IOException {
       BufferedImage fragment;
       File file = new File(fragmentPath);
       fragment = ImageIO.read(file);
       return fragment;
    }

}
