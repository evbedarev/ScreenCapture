package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.BeachDun03;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.monstersOnLocation.MonstersBeachDun03;
import logic.move_by_card.*;
import logic.take_loot.*;
import main.Prop;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicBeachDun03 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicBeachDun03() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        actions = Actions.instance();
        moveByCard = MoveByCard.getInstance(this, new PointsBeachDun03());
        locationCheck = new LocationCheck(new BeachDun03());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of( new MonstersBeachDun03())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
        };
        

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Coupon(),
        };
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        if (countStartProgram == 0) {
            MoveToLocation moveToLocation = new MoveToLocation(this, new PointsComodo());
            moveToLocation.move();
            moveToLocation.lastAction(new int[]{965, 429});
        }
        moveByCard.move(killMonsterList);
        countStartProgram++;
    }

    void teleport() throws Exception {
        runFromMonster();
        if (count > Prop.COUNT_TO_TELEPORT) {
//            lootAround.takeLootAround();
            sleep(500);
            actions.pickUpCard();
            actions.pickUpLoot(locationCheck);
            count = 0;
            actions.teleport(locationCheck);
//            actions.stepAside(locationCheck, new int[] {75, 150} );
//            checkSP.regenSP();
        }
    }

    void runFromMonster() throws Exception {
    }

}
