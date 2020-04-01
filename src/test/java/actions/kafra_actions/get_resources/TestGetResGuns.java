package actions.kafra_actions.get_resources;

import checks.LocationCheck;
import checks.location.VerifyMap;
import logic.LogicLocation;
import main.Prop;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class TestGetResGuns {
    GetResourcesGunslinger getRes = new GetResourcesGunslinger();

    public TestGetResGuns() throws AWTException {
    }

    @Before
    public void before() throws Exception {
        Prop.initialize();
        Prop.takeScreenShotThread.start();
        Thread.sleep(2000);
    }

    @Test
    public void getBullets() throws Exception {
        getRes.get();
    }
}
