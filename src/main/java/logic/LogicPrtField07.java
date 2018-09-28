package logic;

import actions.SleepTime;
import checks.LocationCheck;
import checks.location.PrtField07;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.Rocker;
import logic.move_by_card.MoveByCard;
import logic.take_loot.*;
import main.Prop;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicPrtField07 extends LogicLocation {
    private static final int COUNT_OF_ATTACKS = 100;
    Keys keys;
    Mouse mouse;


    public LogicPrtField07(int threadId) throws Exception {
        mouse = Mouse.getInstance();
        keys = Keys.getInstance();
        moveByCard = MoveByCard.getInstance();
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        locationCheck = new LocationCheck(new PrtField07());
        lootAround.initialize(new HandYun11());
        killMonsterList = Stream
                .of(new Rocker())
                .collect(Collectors.toList());


        usefulLoot = new TakeLoot[] {
                new Card(),
                new Shield(),
                new Coupon()
        };

        loot = new TakeLoot[] {
        };
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
//        moveByCard.initialize(new int[] {1473, 1575, 52, 155});
//        locationCheck.locationCheck();
//        checkDie.check();
//        checkHP.checkHp();
//        moveByCard.move(locationCheck, killMonsterList);
//        killMonsterList.forEach(this::findAndKill);
//        count++;
        actions.useWing();
        SleepTime.sleep(5000);
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
            actions.pickUpLoot(locationCheck);
            count = 0;
            actions.teleport(locationCheck);
            actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }

    void runFromMonster() throws Exception {
    }

}
