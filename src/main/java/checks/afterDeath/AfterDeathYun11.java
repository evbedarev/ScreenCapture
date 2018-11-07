package checks.afterDeath;

import actions.Actions;
import actions.InterfaceActions;
import actions.SleepTime;
import checks.CheckDie;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import main.Prop;
import routes.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AfterDeathYun11 extends CheckDie {

    public AfterDeathYun11() {
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

        SleepTime.sleep(5*1000*60);
        actions.standUp();
        SleepTime.sleep(1000);
        keys.keyPress(KeyEvent.VK_F7);
        SleepTime.sleep(10000);

        RouteFromAldebaran routeFromAldebaran = new RouteFromAldebaran();
        routeFromAldebaran.moveToLocation();

        RouteToLocation route = new RouteYunField01();
        route.tpUntil();
        route = new RouteYunField12();
        route.tpUntil();
    }

    @Override
    public void goToKafra() throws AWTException, InterruptedException {
        Mouse mouse = Mouse.getInstance();
        mouse.mouseClick(955,105);
        SleepTime.sleep(5000);
    }

    public void putItemTest() throws Exception {
        actions = Actions.instance();
        Keys keys = Keys.getInstance();
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

        SleepTime.sleep(10*1000*60);
        actions.standUp();
        SleepTime.sleep(1000);
        keys.keyPress(KeyEvent.VK_F7);
        SleepTime.sleep(10000);

        RouteFromAldebaran routeFromAldebaran = new RouteFromAldebaran();
        routeFromAldebaran.moveToLocation();

        RouteToLocation route = new RouteYunField01();
        route.tpUntil();
        route = new RouteYunField12();
        route.tpUntil();
        System.out.println("On location");
    }
}
