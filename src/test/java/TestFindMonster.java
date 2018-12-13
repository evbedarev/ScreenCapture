import find_fragments.FindFragmentFiles;
import logic.kill_monster.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.awt.image.BufferedImage;
import java.util.List;

public class TestFindMonster {
    private static final String PATH_TO_IMAGE = "Test\\FindMonster\\";
    KillMonster monster;
    List<KillMonster> monstersDoNotFit;
    private List<BufferedImage> imageList;
    private FindFragmentFiles findFragmentFiles = FindFragmentFiles.getInstance();


    @Before
    public void init() throws Exception {
        monster = new WraithDeath();
        monstersDoNotFit.add(new Wraith());
        monstersDoNotFit.add(new Mimic());
        monstersDoNotFit.add(new EvilDruid());
        imageList = findFragmentFiles.fragments("frag*", PATH_TO_IMAGE);
    }

    @Test
    public void findMonster() throws Exception {
        for (int i = 0; i < imageList.size() - 1; i++) {
            Assert.assertTrue(monster.findMonster(imageList.get(i)));
        }
    }

    @Test
    public void discardMonster() throws Exception {
        for (int i = 0; i < imageList.size() - 1; i++) {
            for (KillMonster killMonster : monstersDoNotFit) {
                Assert.assertFalse(killMonster.findMonster(imageList.get(i)));
            }
        }
    }

}
