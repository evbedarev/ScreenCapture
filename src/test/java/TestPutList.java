import actions.kafra_actions.put_loot.PutLoot;
import main.Prop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPutList {
    @Before
    public void before() throws Exception {
        Prop.initialize();
        Prop.takeScreenShotThread.start();
        Thread.sleep(2000);
    }
    @Test
    public void testPutLootToKafraCmdField02() throws Exception {
        PutLoot loot = new PutLoot();
        Thread.sleep(5000);
        loot.putLootToKafra();
    }
    @After
    public void after() {
        Prop.takeScreenShotThread.interrupt();
    }
}
