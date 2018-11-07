//import find_image.FindImageHard;
//import org.junit.Test;
//import storage_image.StorageImageFile;
//
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.Optional;
//
//import static org.junit.Assert.assertTrue;
//
//public class TestFindImageHard {
//    private static final String ROOT_DIR = "/home/mj/Projects/ScreenCapture/src/main/resources/for_test/";
//    private static final String IMAGE_FILE = "fragment_picture.png";
//    FindImageHard findImageHard;
//    BufferedImage image;
//
//    @Test
//    public void testFindPixel() throws IOException {
//        findImageHard = new FindImageHard();
//        image = new StorageImageFile().load(ROOT_DIR + IMAGE_FILE);
//        Optional<int[]> pixels;
//        pixels = findImageHard.findPixel(image, new int[]{-395017});
//        assertTrue(pixels.isPresent());
//    }
//}
