import find_fragments.FindFragmentFiles;
import find_fragments.FindFragments;
import find_image.FindImage;
import find_image.FindImageHard;
import org.junit.Test;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;
import java.util.Optional;
import static org.junit.Assert.assertTrue;

public class TestFindImageExclude {

    @Test
    public void findImageExcludeArea() throws Exception {
        FindImage findImage = new FindImageHard();
        StorageImageFile storageImageFile = new StorageImageFile();
        FindFragments findFragments = new FindFragmentFiles("fragm*", "./src/main/resources/for_test/");
        BufferedImage image = storageImageFile.load("./src/main/resources/for_test/scr1.png");
        Optional<int[]> intArr = findImage.findImageExcludeArea(image, findFragments.fragments().iterator().next(),
                new int[]{500, 650, 25, 110});
        assertTrue(!intArr.isPresent());
    }
}
