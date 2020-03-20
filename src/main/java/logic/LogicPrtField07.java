package logic;

import checks.LocationCheck;
import checks.location.PrtField07;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.KillMonster;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsPrtField07;
import logic.take_loot.*;
import main.Prop;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicPrtField07 extends LogicLocation {
    private static final int COUNT_OF_ATTACKS = 100;
    Keys keys;
    Mouse mouse;


    public LogicPrtField07() throws Exception {
        mouse = Mouse.getInstance();
        keys = Keys.getInstance();
        moveByCard = MoveByCard.getInstance(this, new PointsPrtField07());
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        locationCheck = new LocationCheck(new PrtField07());
        lootAround.initialize(new HandYun11());
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersPrtField07");

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
        moveByCard.move(killMonsterList);
    }

    public void checkMyHp() throws Exception {
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
