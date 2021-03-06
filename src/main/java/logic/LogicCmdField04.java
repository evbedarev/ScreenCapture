package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.GefField05;
import logic.attacks.AttackGef05;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.monstersOnLocation.MonstersCmdField04;
import logic.kill_monster.monstersOnLocation.MonstersGef05;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsCmdField04;
import logic.move_by_card.PointsGefFields05;
import logic.take_loot.*;
import main.Prop;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicCmdField04 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);


    public LogicCmdField04() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef05();
        actions = Actions.instance();
        moveByCard = MoveByCard.getInstance(this,  new PointsCmdField04());
        locationCheck = new LocationCheck(new GefField05());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of(
                        new MonstersCmdField04()
                ).collect(Collectors.toList());

        usefulLoot = new TakeLoot[] {
                new Card(),
//            new Clothes(),
//                new Shield(),
//            new Mask(logger),
                new Coupon()
        };

        loot = new TakeLoot[] {
                new Cyfar(),
                new SeaOtterFur(),
                new GlassBead()


//            new PowderOfButterfly(logger),
        };
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        moveByCard.move(killMonsterList);

//        locationCheck.locationCheck();
//        checkSP.enoghtSP();
//        killMonsterList.forEach(this::findAndKill);
//        checkMyHp();
//        actions.pickUpCard();
//        actions.pickUpLoot(locationCheck);
//        teleport();
//        count++;
//        Prop.cast.cast();
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
