package checks.afterDeath;

import actions.InterfaceActions;
import actions.SleepTime;
import checks.CheckDie;
import key_and_mouse.Mouse;
import main.Prop;
import routes.RouteGefField10;
import routes.RouteToLocation;

import java.awt.*;

public class AfterDeathYunField04 extends CheckDie {

    public AfterDeathYunField04() {
    }


    @Override
    public void startActions() throws Exception {
        InterfaceActions interfaceActions = InterfaceActions.getInstance();
        kafraLootList.add(new KafraLoot(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\Iron\\"));
        kafraLootList.add(new KafraLoot(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\Scell\\"));

        putItems(kafraLootList);

        SleepTime.sleep(3*1000*60);
        interfaceActions.pressOnKafra();
        interfaceActions.pressNext();
        interfaceActions.pressKafraTeleport();
        interfaceActions.pressOk();
        interfaceActions.pressNext();
        interfaceActions.pressGefField10();
        interfaceActions.pressOk();
        SleepTime.sleep(10000);

        RouteToLocation routeGef = new RouteGefField10();
        routeGef.tpUntil();
    }

    @Override
    public void goToKafra() throws AWTException, InterruptedException {
        Mouse mouse = Mouse.getInstance();
        for (int i=0; i < 5; i ++) {
            mouse.mouseClick(785, 42);
            SleepTime.sleep(5000);
        }

//        mouse.mouseClick(1222,229);
        SleepTime.sleep(5000);
    }
}
