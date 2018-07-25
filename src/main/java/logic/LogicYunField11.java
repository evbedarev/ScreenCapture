package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.YunField11;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.*;
import logic.take_loot.*;
import main.Prop;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicYunField11 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    private final int threadId;
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);

    public LogicYunField11(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        this.threadId = threadId;
        actions = Actions.instance();
        
        locationCheck = new LocationCheck(new YunField11());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, locationCheck);
        killMonsterList = Stream
                .of(new Goat())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new AntelopeSkin(),
                new BlueHerb(),
                new Bottle()
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Bottle(),
                new BlueHerb(),
                new Coupon(),
        };
    }

    @Override
    public void createThread() throws Exception {
//        Thread thread = new LogicYunField11(1);
//        thread.start();
        start();
    }

    public void mainHandle() throws Exception {
        if (threadId == 0) {
            checkCast();
            if (checkDie.check()) {
                while (true) {
                    Thread.sleep(5000);
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

        if (threadId == 1) {
            ATOMIC_GUARD.incrementAndGet();
            ATOMIC_AWAKENING.incrementAndGet();
            ATOMIC_DEFENDER.incrementAndGet();
            ATTACK_TIMER.incrementAndGet();
            sleep(10000);
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

    void runFromMonster() throws Exception {
    }

}
