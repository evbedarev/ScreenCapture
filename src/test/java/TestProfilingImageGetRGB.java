import logic.Capture;
import org.junit.Before;
import org.junit.Test;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class TestProfilingImageGetRGB {
    StorageImageFile storageImageFile;
    BufferedImage image;
    Capture capture;

    @Before
    public void createBufferedImage() throws Exception {
        storageImageFile = StorageImageFile.instance();
        capture = Capture.instance();
    }

    @Test
    public void profilingBufferedImage() throws Exception {
        double timeBefore = System.currentTimeMillis();
//        image = storageImageFile.load("C:\\TEMP\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment.png");
        image = capture.takeScreenShot();
        image.getRGB(800, 450);
        double timeAfter = System.currentTimeMillis();
        System.out.println("profilingBufferedImage: " + (timeAfter - timeBefore));
    }

    @Test
    public void profilingArrayGetRgbByIndex() throws Exception {
        double timeBefore = System.currentTimeMillis();
        int [] rgb = null;
//        image = storageImageFile.load("C:\\TEMP\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment.png");
        image = capture.takeScreenShot();
        double timeAfter = System.currentTimeMillis();
        System.out.println(rgb.length);
        System.out.println("profilingArray: " + (timeAfter - timeBefore));
    }

    // USED FOR BMP/PNG BUFFERED_IMAGE
    private int[][] loadFromFile(BufferedImage image) {
        int[][] rgbData = null;
        final byte[] pixels = ((DataBufferByte) image.getData().getDataBuffer())
                .getData();
        final int width = image.getWidth();

        if (rgbData == null)
            rgbData = new int[image.getHeight()][width];

        for (int pixel = 0, row = 0; pixel < pixels.length; row++)
            for (int col = 0; col < width; col++, pixel += 3)
                rgbData[row][col] = -16777216 + ((int) pixels[pixel] & 0xFF)
                        + (((int) pixels[pixel + 1] & 0xFF) << 8)
                        + (((int) pixels[pixel + 2] & 0xFF) << 16); // 255
        // alpha, r
        // g b;

        return rgbData;
    }




}
