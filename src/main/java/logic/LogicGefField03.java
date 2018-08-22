package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.GefField03;
import checks.location.YunField11;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.Goat;
import logic.kill_monster.Orc;
import logic.kill_monster.OrcLady;
import logic.take_loot.*;
import main.Prop;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicGefField03 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicGefField03(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        actions = Actions.instance();
        
        locationCheck = new LocationCheck(new GefField03());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of( new Orc(),
                        new OrcLady())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new AntelopeSkin(),
                new BlueHerb(),
                new Bottle()
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Bottle(),
                new BlueHerb(),
                new Coupon(),
        };
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        Prop.cast.cast();
        checkDie.check();
//        if (checkDie.check()) {
//            while (true) {
//                SleepTime.sleep(5000);
//            }
//        }
        locationCheck.locationCheck();
        checkSP.enoghtSP();
        killMonsterList.forEach(this::findAndKill);
//        checkMyHp();
        actions.pickUpCard();
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
            sleep(500);
            actions.pickUpCard();
            count = 0;
            actions.teleport(locationCheck);
            actions.stepAside(locationCheck, new int[] {75, 150} );
            checkSP.regenSP();
        }
    }

    void runFromMonster() throws Exception {
    }

}
