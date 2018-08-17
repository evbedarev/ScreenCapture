package logic;

import actions.Actions;
import actions.SleepTime;
import checks.LocationCheck;
import checks.location.PrtField07;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.Rocker;
import logic.take_loot.*;
import main.Prop;

import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicPrtField07 extends LogicLocation {
    private static final int COUNT_OF_ATTACKS = 100;
    Keys keys;
    Mouse mouse;


    public LogicPrtField07(int threadId) throws Exception {
        mouse = Mouse.getInstance();
        keys = Keys.getInstance();
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        locationCheck = new LocationCheck(new PrtField07());
        lootAround.initialize(new HandYun11());
        killMonsterList = Stream
                .of(new Rocker())
                .collect(Collectors.toList());


        usefulLoot = new TakeLoot[] {
                new Card(),
//            new Clothes(logger),
                new Shield(),
//            new Mask(logger),
                new Coupon()
        };

        loot = new TakeLoot[] {
//            new AntelopeSkin(logger),
//            new BlueHerb(logger),
//            new Bottle(logger)
        };
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
//        actions.teleport();
        mouse.mouseClick(100, 100);

        keys.keyPress(KeyEvent.VK_F1);

        SleepTime.sleep(2000);
        keys.keyPress(KeyEvent.VK_ENTER);
////        locationCheck.locationCheck();
//        killMonsterList.forEach(this::findAndKill);
//        checkMyHp();
//        actions.pickUpCard();
//        actions.pickUpLoot();
////        if (checkSP.enoghtSP()) {
////            actions.sitDown();
////            SleepTime.sleep(60 * 1000);
////            actions.standUp();
////            SleepTime.sleep(1000);
////        }
////        teleport();
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
