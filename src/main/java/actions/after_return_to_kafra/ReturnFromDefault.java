package actions.after_return_to_kafra;

import actions.Actions;
import actions.InterfaceActions;
import actions.SleepTime;
import actions.kafra_actions.get_resources.GetResourcesGunslinger;
import checks.location.VerifyMap;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.LogicCmdField02;
import logic.move_by_card.MoveToLocation;
import logic.move_by_card.PointsComodo;
import logic.move_by_card.PointsComodo2;
import logic.move_by_card.PointsPapuchichaForest;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ReturnFromDefault extends ReturnFrom{
    public ReturnFromDefault() throws AWTException {
        getResources = new GetResourcesGunslinger();
    }

    @Override
    public void startAction() throws Exception {
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
        while (true) {
            SleepTime.sleep(3000);
        }
    }
}
