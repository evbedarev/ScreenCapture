package logic.LogicWizard;

import actions.SleepTime;
import checks.LocationCheck;
import checks.location.MagmaDun01;
import checks.location.YunField04;
import key_and_mouse.Keys;
import logger.LoggerSingle;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun04;
import logic.kill_monster.*;
import logic.take_loot.*;
import main.Prop;

import java.awt.event.KeyEvent;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicWizardMagmaDun01 extends LogicLocationWizard {

    private static final int COUNT_OF_ATTACKS = 100;
    Keys keys;
    KillMonster awareMonster;
    public LogicWizardMagmaDun01(int threadId) throws Exception {
        keys = Keys.getInstance();
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        locationCheck = new LocationCheck(new MagmaDun01());
        lootAround.initialize(new HandYun04());
        killMonsterList = Stream
                .of(new LavaGolem(),
                        new Blazer(),
                        new Kaho())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Coupon(),
        };

        awareMonster = new FuriousExplosion();

        checkAgressorIsNear.initialize(Stream
                .of(new LavaGolem(),
                        new Blazer(),
                        new Kaho(),
                        new Explosion(),
                        new FuriousExplosion())
                .collect(Collectors.toList()));
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    private boolean findMonstersNear() throws Exception{
        if (awareMonster.findMonster()) {
            actions.teleport(locationCheck);
            LoggerSingle.logInfo(this.toString(), "Running from monster");
        }

        for (KillMonster monster: killMonsterList) {
            checkHP.checkHp();
            if (monster.findMonster()) {
                keys.keyPress(KeyEvent.VK_F7);
                SleepTime.sleep(3000);
                checkHP.checkHp();
//                keys.keyPress(KeyEvent.VK_F5);
//                SleepTime.sleep(4000);
                actions.stepAside(locationCheck, new int[] {75, 150} );
                keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_1);
            }
        }
        return false;
    }
    public void mainHandle() throws Exception {
        if (checkDie.check()) {
            while (true) {
                SleepTime.sleep(5000);
            }
        }
        locationCheck.locationCheck();
//        checkSP.enoghtSP();
        findMonstersNear();
        checkMyHp();
        actions.pickUpCard();
        actions.pickUpLoot();
        teleport();
        count++;
    }

    //RENAME
    void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
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
            if (!findMonstersNear())
                actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }

    void runFromMonster() throws Exception {
    }

}
