package checks.afterDeath;

import actions.Actions;
import actions.InterfaceActions;
import checks.CheckDie;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import main.Prop;
import routes.RouteFromAldebaran;
import routes.RouteToLocation;
import routes.RouteYunField01;
import routes.RouteYunField12;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AfterDeathCmd07 extends CheckDie {

    public AfterDeathCmd07() {
    }


    @Override
    public void startActions() throws Exception {
        actions = Actions.instance();
        Keys keys = Keys.getInstance();
        InterfaceActions interfaceActions = InterfaceActions.getInstance();
        kafraLootList.add(new KafraLoot(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                Prop.ROOT_DIR + "Loot\\BlueHerb\\"));

        kafraLootList.add(new KafraLoot(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                Prop.ROOT_DIR + "Loot\\GreenHerb\\"));

        kafraLootList.add(new KafraLoot(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                Prop.ROOT_DIR + "Loot\\YellowHerb\\"));

        kafraLootList.add(new KafraLoot(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\Bottle\\"));

        kafraLootList.add(new KafraLoot(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\AntelopeSkin\\"));

        kafraLootList.add(new KafraLoot(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\AntelopeHorn\\"));

        putItems(kafraLootList);

        Thread.sleep(5*1000*60);

    }

    @Override
    public void goToKafra() throws AWTException, InterruptedException {
        Mouse mouse = Mouse.getInstance();
        mouse.mouseClick(1275,37);
        Thread.sleep(5000);
    }
}
