package actions.kafra_actions.get_resources;

import actions.kafra_actions.put_loot.PutLoot;
import main.Prop;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class TestGetResGuns {
    GetResourcesGunslinger getRes = new GetResourcesGunslinger();
    PutLoot putLoot = new PutLoot();

    public TestGetResGuns() throws AWTException {
    }

    @Before
    public void before() throws Exception {
        Prop.initialize();
//        Prop.takeScreenShotThread.start();
        Thread.sleep(2000);
    }

    @Test
    public void getBullets() throws Exception {
        putLoot.putLootToKafra();
        getRes.get();
    }
}
