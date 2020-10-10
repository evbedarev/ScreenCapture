package checks.afterDeath;

import actions.Actions;
import actions.InterfaceActions;
import actions.SleepTime;
import checks.CheckDie;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import main.Prop;
import routes.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AfterDeathPrt03 extends CheckDie {

    @Override
    public void startActions() throws Exception {
        actions = Actions.instance();
        InterfaceActions interfaceActions = InterfaceActions.getInstance();
        interfaceActions.pressReturnToLastSavepoint();
        mouse = Mouse.getInstance();
        mouse.mouseMove(0,0);
        SleepTime.sleep(1000);
        interfaceActions.pressOk();
        SleepTime.sleep(15000);
        if (!interfaceActions.pressCharSelect()) {
            LoggerSingle.logInfo(this.toString(), "Cant open charSelect!!!");
        }
        Keys keys = Keys.getInstance();
        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_F4);
        while (true) {
            SleepTime.sleep(3000);
        }
    }

    @Override
    public void goToKafra() throws AWTException, InterruptedException {
        Mouse mouse = Mouse.getInstance();
        mouse.mouseClick(955,105);
        SleepTime.sleep(5000);
    }
}
