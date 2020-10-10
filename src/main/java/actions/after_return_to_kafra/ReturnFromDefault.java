package actions.after_return_to_kafra;

import actions.InterfaceActions;
import actions.SleepTime;
import actions.kafra_actions.get_resources.GetResourcesGunslinger;
import key_and_mouse.Keys;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ReturnFromDefault extends ReturnFrom{
    public ReturnFromDefault() throws AWTException {
        getResources = new GetResourcesGunslinger();
    }

    @Override
    public void startAction() throws Exception {
        keys = Keys.getInstance();
        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_F4);
        while (true) {
            SleepTime.sleep(3000);
        }
    }
}
