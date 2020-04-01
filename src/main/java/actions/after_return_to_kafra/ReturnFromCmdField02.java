package actions.after_return_to_kafra;
import actions.SleepTime;
import actions.kafra_actions.get_resources.GetResourcesGunslinger;
import checks.location.VerifyMap;
import logic.LogicBeachDun03;
import logic.move_by_card.MoveToLocation;
import logic.move_by_card.PointsComodo;
import logic.move_by_card.PointsComodo2;
import logic.move_by_card.PointsPapuchichaForest;
import java.awt.*;

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
        moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsComodo());
        moveToLocation.move(true);
        mouse.mouseClick(1240,790);
        Thread.sleep(2000);
        mouse.mouseClick(1340,120);
        Thread.sleep(4000);
        moveToLocation.lastAction(new int[]{965, 429}, (VerifyMap) context.getBean("beachDun03"));
        Thread.sleep(2000);
    }
    public void moveInComodoDung() throws Exception {
        moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsComodo2());
        moveToLocation.move(true);
        mouse.mouseClick(1090,850);
        Thread.sleep(2000);
        mouse.mouseClick(1200,260);
        Thread.sleep(4000);
        moveToLocation.lastAction(new int[]{910, 540}, (VerifyMap) context.getBean("cmdField01"));
        SleepTime.sleep(1000);
    }
    public void movePapuchichaaForest() throws Exception {
        moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsPapuchichaForest());
        moveToLocation.move(true);
        mouse.mouseClick(1090,600);
        Thread.sleep(2000);
        mouse.mouseClick(470,700);
        Thread.sleep(4000);
        moveToLocation.lastAction(new int[]{660, 560}, (VerifyMap) context.getBean("cmdField02"));
    }
}
