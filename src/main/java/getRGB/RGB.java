package getRGB;

import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;

public class RGB {
    public static void main(String[] args) throws Exception {
        StorageImage storageImage = new StorageImageFile();
        BufferedImage image = storageImage.load("C:\\java\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment.png");
        BufferedImage image1 = storageImage.load("C:\\java\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment1.png");
        System.out.println("Fragment1 " + image.getRGB(1387,162));
        System.out.println("Fragment2 " + image1.getRGB(432,567));
    }
}
