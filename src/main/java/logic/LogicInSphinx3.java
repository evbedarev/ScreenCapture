package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.GefField05;
import logic.attacks.AttackGef05;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.monstersOnLocation.MonstersGef05;
import logic.kill_monster.monstersOnLocation.MonstersInSphinx3;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsGefFields05;
import logic.move_by_card.PointsInSphinx3;
import logic.take_loot.Card;
import logic.take_loot.Coupon;
import logic.take_loot.TakeLoot;
import main.Prop;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicInSphinx3 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);


    public LogicInSphinx3() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef05();
        actions = Actions.instance();
        moveByCard = MoveByCard.getInstance(this, new PointsInSphinx3());
        locationCheck = new LocationCheck(new GefField05());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of(
                        new MonstersInSphinx3()
                ).collect(Collectors.toList());

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Coupon()
        };

        loot = new TakeLoot[] {
//            new PowderOfButterfly(logger),
        };
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        moveByCard.move(killMonsterList);
    }

//    public void checkMyHp() throws Exception {
////        actions.pickUpCard();
////        checkHP.checkHp();
//    }

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
