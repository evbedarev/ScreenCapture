package actions.after_return_to_kafra;
import actions.SleepTime;
import actions.kafra_actions.get_resources.GetResourcesGunslinger;
import checks.location.VerifyMap;
import logger.LoggerSingle;
import logic.LogicCmdField02;
import logic.move_by_card.MoveToLocation;
import logic.move_by_card.PointsComodo;
import logic.move_by_card.PointsComodo2;
import logic.move_by_card.PointsPapuchichaForest;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class ReturnFromCmdField02 extends ReturnFrom{
    public ReturnFromCmdField02() throws AWTException {
        getResources = new GetResourcesGunslinger();
    }

    @Override
    public void startAction() throws Exception {
        SleepTime.sleep(20000);
        putLoot.putLootToKafra();
        SleepTime.sleep(10000);
        getResources.get();
        SleepTime.sleep(10000);
        moveInComodo();
        moveInComodoDung();
        movePapuchichaaForest();

    }
    private void moveInComodo() throws Exception {
        moveToLocation = new MoveToLocation(new LogicCmdField02(), new PointsComodo());
        LoggerSingle.logInfo(this.toString(), "moving around the map Comodo");
        boolean hasComeComodo = moveToLocation.move(true);
        while (!hasComeComodo) {
            keys.keyPress(KeyEvent.VK_F7);
            SleepTime.sleep(2000);
            getResources.get();
            hasComeComodo = moveToLocation.move(true);
        }
        mouse.mouseClick(1240,790);
        Thread.sleep(2000);
        mouse.mouseClick(1340,120);
        Thread.sleep(4000);
        moveToLocation.lastAction(new int[]{965, 429}, (VerifyMap) context.getBean("beachDun03"));
        Thread.sleep(2000);
    }

    public void moveInComodoDung() throws Exception {
        moveToLocation = new MoveToLocation(new LogicCmdField02(), new PointsComodo2());
        LoggerSingle.logInfo(this.toString(), "moving around the map Comodo dungeon");
        boolean hasComeComodoDung = moveToLocation.move(true);
        while (!hasComeComodoDung) {
            keys.keyPress(KeyEvent.VK_F7);
            SleepTime.sleep(2000);
            getResources.get();
            moveInComodo();
            moveToLocation = new MoveToLocation(new LogicCmdField02(), new PointsComodo2());
            hasComeComodoDung = moveToLocation.move(true);
        }
        mouse.mouseClick(1090,850);
        Thread.sleep(2000);
        mouse.mouseClick(1200,260);
        Thread.sleep(4000);
        moveToLocation.lastAction(new int[]{910, 540}, (VerifyMap) context.getBean("cmdField01"));
        SleepTime.sleep(1000);
    }
    public void movePapuchichaaForest() throws Exception {
        moveToLocation = new MoveToLocation(new LogicCmdField02(), new PointsPapuchichaForest());
        LoggerSingle.logInfo(this.toString(), "moving around the map papuchaForest");
        boolean papuchForest = moveToLocation.move(true);
        while (!papuchForest ) {
            keys.keyPress(KeyEvent.VK_F7);
            SleepTime.sleep(2000);
            getResources.get();
            moveInComodo();
            moveInComodoDung();
            moveToLocation = new MoveToLocation(new LogicCmdField02(), new PointsPapuchichaForest());
            papuchForest = moveToLocation.move(true);
        }
        mouse.mouseClick(1090,600);
        Thread.sleep(2000);
        mouse.mouseClick(470,700);
        Thread.sleep(4000);
        moveToLocation.lastAction(new int[]{660, 560}, (VerifyMap) context.getBean("cmdField02"));
    }
}
