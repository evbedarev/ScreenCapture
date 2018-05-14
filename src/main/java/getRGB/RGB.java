package getRGB;

import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;
import java.security.KeyStore;
import java.util.*;

public class RGB {
    public static void main(String[] args) throws Exception {
//        System.out.println("________________________________________");
//        findRGB("/home/mj/Projects/ScreenCapture/src/main/resources/getRGB/frag.png", "/home/mj/Projects/ScreenCapture/src/main/resources/getRGB/frag1.png");
//        System.out.println("________________________________________");
//        findRGB("/home/mj/Projects/ScreenCapture/src/main/resources/getRGB/frag.png", "/home/mj/Projects/ScreenCapture/src/main/resources/getRGB/frag2.png");
//        System.out.println("________________________________________");
//        findRGB("/home/mj/Projects/ScreenCapture/src/main/resources/getRGB/frag.png", "/home/mj/Projects/ScreenCapture/src/main/resources/getRGB/frag3.png");
//        System.out.println("________________________________________");
//        findRGB("/home/mj/Projects/ScreenCapture/src/main/resources/getRGB/frag.png", "/home/mj/Projects/ScreenCapture/src/main/resources/getRGB/frag4.png");
        findPixelRgb();
    }

    public static void findRGB(String path1, String path2) throws Exception {
        HashMap<Integer, List<RgbClass>> mapRgb = new HashMap<>();
        StorageImage storageImage = new StorageImageFile();
        List<RgbClass> rgbClassList = new ArrayList<>();

        BufferedImage image = storageImage.load(path1);
        BufferedImage fragment = storageImage.load(path2);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x=0; x < image.getWidth(); x++) {

                for (int yy = 0; yy < fragment.getHeight(); yy++) {
                    for (int xx = 0; xx < fragment.getWidth(); xx++) {

                        if (image.getRGB(x, y) == fragment.getRGB(xx , yy)) {
                            rgbClassList = new ArrayList<>();
                            rgbClassList.add(new RgbClass(x, y));
                            mapRgb.put(image.getRGB(x, y), rgbClassList);
                        }
                    }
                }
            }
        }

        for (Map.Entry<Integer, List<RgbClass>> entry: mapRgb.entrySet()) {
            System.out.printf(entry.getKey() + " RGB  ");
            for (RgbClass rgbClass: entry.getValue()) {
                System.out.println("x: " + rgbClass.getX() + " y: " + rgbClass.getY());
            }
        }
    }

    public static void findPixelRgb() throws Exception {
        StorageImage storageImage = new StorageImageFile();
        BufferedImage image = storageImage.load("/home/mj/Projects/ScreenCapture/src/main/resources/getRGB/fragment1.png");
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x=0; x < image.getWidth(); x++) {
                if (image.getRGB(x,y) == -3552801) {
                    System.out.println("x=" + x + " y=" + y);
                }
            }
        }

    }
}
