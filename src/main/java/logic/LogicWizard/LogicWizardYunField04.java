package logic.LogicWizard;

import actions.SleepTime;
import checks.LocationCheck;
import checks.location.YunField04;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun04;
import logic.kill_monster.Harpy;
import logic.take_loot.*;
import main.Prop;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicWizardYunField04 extends LogicLocationWizard {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicWizardYunField04(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        locationCheck = new LocationCheck(new YunField04());
        lootAround.initialize(new HandYun04());
        killMonsterList = Stream
                .of(new Harpy())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
//                new BlueHerb(),
//                new Bottle(),
////                new AntelopeSkin(),
                new HarpyFeather(),
                new HarpyTalon()
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
//                new Shield(),
//                new HarpyFeather(),
//                new Bottle(),
//                new BlueHerb(),
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
//        checkSP.enoghtSP();
        killMonsterList.forEach(this::findAndKill);
        checkMyHp();
        actions.pickUpCard();
        actions.pickUpLoot();
        teleport();
        count++;
    }

    //RENAME
    void checkMyHp() throws Exception {
        actions.pickUpCard();
    }

    void teleport() throws Exception {
        runFromMonster();
        if (count > Prop.COUNT_TO_TELEPORT) {
            sleep(500);
            actions.pickUpCard();
            actions.pickUpLoot();
            count = 0;
            Prop.cast.cast();
            actions.teleport(locationCheck);
            actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }

    void runFromMonster() throws Exception {
    }

}