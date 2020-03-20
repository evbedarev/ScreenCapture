package logic;

import actions.Actions;
import actions.SleepTime;
import checks.LocationCheck;
import checks.location.BeachDun02;
import logic.attacks.AttackBeachDun02;
import logic.hands_rgb.HandBeachDun02;
import logic.kill_monster.KillMonster;
import logic.take_loot.*;
import main.Prop;
import java.util.List;

public class LogicBeachDun02 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicBeachDun02() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackBeachDun02();
        actions = Actions.instance();
        
        locationCheck = new LocationCheck(new BeachDun02());
        lootAround.initialize(new HandBeachDun02());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersBeachDun02");

        loot = new TakeLoot[] {
                new Brigan(),
                new MudLamp()
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Coupon(),
                new Elunium()
        };
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        Prop.cast.cast();
        if (checkDie.check()) {
            while (true) {
                SleepTime.sleep(5000);
            }
        }
        locationCheck.locationCheck();
        checkSP.enoghtSP();
        killMonsterList.forEach(this::findAndKill);
        checkMyHp();
        actions.pickUpCard();
        actions.pickUpLoot(locationCheck);
        teleport();
        count++;
    }

    public void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    void teleport() throws Exception {
        runFromMonster();
        if (count > Prop.COUNT_TO_TELEPORT) {
            lootAround.takeLootAround();
            sleep(500);
            actions.pickUpCard();
            actions.pickUpLoot(locationCheck);
            count = 0;
            actions.teleport(locationCheck);
            actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }

    void runFromMonster() throws Exception {
    }

}
