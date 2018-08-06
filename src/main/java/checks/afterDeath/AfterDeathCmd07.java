package checks.afterDeath;

import actions.Actions;
import actions.InterfaceActions;
import actions.SleepTime;
import checks.CheckDie;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
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
        LoggerSingle.logInfo(this.toString(), "Starting actions after death");
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

        interfaceActions.pressOnKafra();
        interfaceActions.pressNext();
        interfaceActions.pressKafraTeleport();
        interfaceActions.pressOk();
        interfaceActions.pressNext();
        interfaceActions.pressDownArrow();
        interfaceActions.pressCmdField07();
        interfaceActions.pressOk();

    }

    @Override
    public void goToKafra() throws AWTException, InterruptedException {
        Mouse mouse = Mouse.getInstance();
        mouse.mouseClick(1433,38);
        SleepTime.sleep(5000);
    }
}
