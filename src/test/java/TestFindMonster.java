import find_fragments.FindFragmentFiles;
import logic.kill_monster.*;
import main.Prop;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TestFindMonster {
    private static final String PATH_TO_IMAGE = Prop.ROOT_DIR + "Test\\FindMonster\\";
    private KillMonster monster;
    List<KillMonster> monstersDoNotFit = new ArrayList<>();
    private List<BufferedImage> imageList = new ArrayList<>();
    List<KillMonster> appropriateMonster = new ArrayList<>();
    private FindFragmentFiles findFragmentFiles = FindFragmentFiles.getInstance();


    @Before
    public void init() throws Exception {
        monster = new WraithDeath();
        appropriateMonster.add(new WraithDeath());
        monstersDoNotFit.add(new Wraith());
        monstersDoNotFit.add(new Mimic());
        monstersDoNotFit.add(new EvilDruid());
        imageList = findFragmentFiles.fragments("frag*", PATH_TO_IMAGE);
    }

    @Test
    public void findMonster() throws Exception {
        long before = System.currentTimeMillis();
        for (int i = 0; i < imageList.size(); i++) {
            Assert.assertTrue(monster.findMonster(imageList.get(i)));
        }
        long after = System.currentTimeMillis();
        System.out.println("Time to find Monster: " + (after - before));
    }

    @Test
    public void discardMonster() throws Exception {
        int cnt = 0;
        long before = System.currentTimeMillis();
        for (int i = 0; i < imageList.size(); i++) {
            for (KillMonster killMonster : monstersDoNotFit) {
                Assert.assertFalse(killMonster.findMonster(imageList.get(i)));
                cnt++;
            }
        }
        long after = System.currentTimeMillis();
        System.out.println("Time to discard " + monstersDoNotFit.size() + " Monster: " + (after - before));
        System.out.println(cnt);
    }

}
