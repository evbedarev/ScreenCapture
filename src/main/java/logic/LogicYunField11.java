package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.YunField11;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun07;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.*;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsGefFields05;
import logic.move_by_card.PointsYunField11;
import logic.take_loot.*;
import main.Prop;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicYunField11 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicYunField11() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        actions = Actions.instance();
        moveByCard = MoveByCard.getInstance(this, new PointsYunField11());
        locationCheck = new LocationCheck(new YunField11());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of(new Goat())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new AntelopeSkin(),
                new Bottle(),
                new AntelopeHorn()
        };
        

        usefulLoot = new TakeLoot[] {
                new Card(),
                new BlueHerb(),
                new Coupon(),
        };
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        moveByCard.move(killMonsterList);
//        Prop.cast.cast();
//        checkDie.check();
////        if (checkDie.check()) {
////            while (true) {
////                SleepTime.sleep(5000);
////            }
////        }
//        locationCheck.locationCheck();
////        checkSP.enoghtSP();
//        killMonsterList.forEach(this::findAndKill);
//        checkMyHp();
//        actions.pickUpCard();
//        actions.pickUpLoot(locationCheck);
//        teleport();
//        count++;
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
