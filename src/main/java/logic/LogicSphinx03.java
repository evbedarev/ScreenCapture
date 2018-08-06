package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.Sphinx03;
import logic.attacks.AttackSphinx03;
import logic.hands_rgb.HandSph03;
import logic.kill_monster.*;
import logic.take_loot.*;
import main.Prop;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicSphinx03 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    private final int threadId;
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);


    public LogicSphinx03(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackSphinx03();
        this.threadId = threadId;
        actions = Actions.instance();
        locationCheck = new LocationCheck(new Sphinx03());
        lootAround.initialize(new HandSph03());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of(new Marduk(), new Pasana())
                .collect(Collectors.toList());

        usefulLoot = new TakeLoot[]{
                new Card(),
////            new Clothes(logger),
                new Shield(),
                new BlueHerb()
////            new Mask(logger),
//            new Coupon(logger)
        };

        loot = new TakeLoot[]{
                new FlameHeart()
//            new HarpyFeather(logger),
//            new HarpyTalon(logger)
        };
    }

    @Override
    public void createThread() throws Exception {
        Thread thread = new LogicSphinx03(1);
        thread.start();
        start();
    }

    public void mainHandle() throws Exception {
        if (threadId == 0) {
            locationCheck.locationCheck();
            checkSP.enoghtSP();
            killMonsterList.forEach(this::findAndKill);
            checkMyHp();
            actions.pickUpCard();
            actions.pickUpLoot();
            teleport();
            count++;
            checkCast();
        }

        if (threadId == 1) {
            ATOMIC_GUARD.incrementAndGet();
            ATOMIC_AWAKENING.incrementAndGet();
            ATOMIC_DEFENDER.incrementAndGet();
            ATTACK_TIMER.incrementAndGet();
            ATTACK_MOBS_BEHIND_WALLS.incrementAndGet();
            sleep(1000);
        }
    }

    void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    void checkCast() throws InterruptedException {
        if (ATOMIC_GUARD.get() > 300) {
            actions.castGuard();
            actions.castReflectShield();
            ATOMIC_GUARD.set(0);
        }

        if (ATOMIC_AWAKENING.get() > 1800) {
            actions.drinkAwaikeningPotion();
            ATOMIC_AWAKENING.set(0);
        }

        if (ATOMIC_DEFENDER.get() > 180) {
//            keys.keyPress(DEFENDER);
            sleep(1000);
            ATOMIC_DEFENDER.set(0);
        }
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

    void runFromMonster() throws

            Exception {
    }

}
