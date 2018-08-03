package checks.afterDeath;

import actions.InterfaceActions;
import checks.CheckDie;
import key_and_mouse.Mouse;
import main.Prop;
import routes.RouteGefField10;
import routes.RouteToLocation;

import java.awt.*;

public class AfterDeathGef11 extends CheckDie {

    public AfterDeathGef11() {
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

        Thread.sleep(5*1000*60);
        interfaceActions.pressOnKafra();
        interfaceActions.pressNext();
        interfaceActions.pressKafraTeleport();
        interfaceActions.pressOk();
        interfaceActions.pressNext();
        interfaceActions.pressGefField10();
        interfaceActions.pressOk();
        Thread.sleep(10000);

        RouteToLocation routeGef = new RouteGefField10();
        routeGef.tpUntil();
    }

    @Override
    public void goToKafra() throws AWTException, InterruptedException {
        Mouse mouse = Mouse.getInstance();
        mouse.mouseClick(410,80);
        Thread.sleep(5000);

        mouse.mouseClick(1222,229);
        Thread.sleep(5000);
    }
}
