package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.BeachDun02;
import checks.location.YunField11;
import logic.attacks.AttackBeachDun02;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.Goat;
import logic.kill_monster.Megalith;
import logic.kill_monster.StalacticGolem;
import logic.kill_monster.TriJoint;
import logic.take_loot.*;
import main.Prop;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicBeachDun02 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicBeachDun02(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackBeachDun02();
        actions = Actions.instance();
        
        locationCheck = new LocationCheck(new BeachDun02());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, locationCheck);
        killMonsterList = Stream
                .of(
                        new Megalith(),
                        new StalacticGolem())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new Brigan(),
                new MudLamp()
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Elunium(),
                new Coupon(),
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
                Thread.sleep(5000);
            }
        }
        locationCheck.locationCheck();
        checkSP.enoghtSP();
        killMonsterList.forEach(this::findAndKill);
        checkMyHp();
        actions.pickUpCard();
        actions.pickUpLoot();
        teleport();
        count++;
    }

    void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    void teleport() throws Exception {
        runFromMonster();
        if (count > Prop.COUNT_TO_TELEPORT) {
            lootAround.takeLootAround();
            sleep(500);
            actions.pickUpCard();
            actions.pickUpLoot();
            count = 0;
            actions.teleport(locationCheck);
            actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }

    void runFromMonster() throws Exception {
    }

}
