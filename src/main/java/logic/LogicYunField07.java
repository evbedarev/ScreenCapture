package logic;

import actions.SleepTime;
import checks.LocationCheck;
import checks.location.YunField07;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.*;
import logic.take_loot.*;
import main.Prop;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicYunField07 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicYunField07(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        locationCheck = new LocationCheck(new YunField07());
        lootAround.initialize(new HandYun11());
        killMonsterList = Stream
                .of(new MonstersYun07())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new BlueHerb(),
                new Bottle(),
//                new AntelopeSkin(),
                new HarpyFeather(),
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Shield(),
                new Bottle(),
                new BlueHerb(),
                new Coupon(),
        };

        checkAgressorIsNear.initialize(Stream
                .of(new Harpy())
                .collect(Collectors.toList()));
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
