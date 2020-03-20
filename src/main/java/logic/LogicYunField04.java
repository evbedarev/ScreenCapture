package logic;

import actions.SleepTime;
import checks.LocationCheck;
import checks.location.YunField04;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun04;
import logic.kill_monster.Harpy;
import logic.kill_monster.KillMonster;
import logic.take_loot.*;
import main.Prop;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicYunField04 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicYunField04() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        locationCheck = new LocationCheck(new YunField04());
        lootAround.initialize(new HandYun04());
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersYunField04");

        loot = new TakeLoot[] {
                new HarpyFeather(),
                new HarpyTalon()
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
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

    //RENAME
    public void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    void teleport() throws Exception {
        runFromMonster();
        if (count > Prop.COUNT_TO_TELEPORT) {
//            lootAround.takeLootAround();
            sleep(500);
            actions.pickUpCard();
            actions.pickUpLoot(locationCheck);
            count = 0;
            Prop.cast.cast();
            actions.teleport(locationCheck);
//            actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }

    void runFromMonster() throws Exception {
    }

}
