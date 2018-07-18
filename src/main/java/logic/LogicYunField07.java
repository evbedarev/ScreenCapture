package logic;

import checks.LocationCheck;
import checks.location.CheckOverweight;
import checks.location.YunField07;
import logger.LoggerSingle;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.*;
import logic.take_loot.*;
import main.Prop;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicYunField07 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    private final int threadId;
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    public static final AtomicInteger TIMER_CHECK_OVERWEIGHT = new AtomicInteger(0);


    public LogicYunField07(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        this.threadId = threadId;
        locationCheck = new LocationCheck(new YunField07());
        lootAround.initialize(new HandYun11());
        killMonsterList = Stream
                .of(new MonstersYun07())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new BlueHerb(),
                new Bottle(),
                new AntelopeSkin(),
                new HarpyFeather(),
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Shield(),
                new Bottle(),
                new BlueHerb(),
                new Coupon(),
        };

        checkAgressorIsNear.initialize(Stream
                .of(new Harpy())
                .collect(Collectors.toList()));
    }

    @Override
    public void createThread() throws Exception {
        Thread thread = new LogicYunField07(1);
        thread.start();
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
            ATTACK_TIMER.incrementAndGet();
            TIMER_CHECK_OVERWEIGHT.incrementAndGet();
            sleep(1000);
        }
    }

    void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    void checkCast() throws Exception {
        if (ATOMIC_GUARD.get() > 300) {
            actions.castGuard();
            actions.castReflectShield();
            ATOMIC_GUARD.set(0);
        }

        if (ATOMIC_AWAKENING.get() > 1800) {
            actions.drinkAwaikeningPotion();
            ATOMIC_AWAKENING.set(0);
        }

        if (TIMER_CHECK_OVERWEIGHT.get() > Prop.TIMER_CHECK_OVERWEIGHT) {
            LoggerSingle.logInfo(this.toString(), "Cheking overweight");
            if (CheckOverweight.check()) {
                LoggerSingle.logInfo(this.toString(), "Dropping greenHerb");
                actions.dropItem(Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                        Prop.ROOT_DIR + "Loot\\GreenHerb\\");
                LoggerSingle.logInfo(this.toString(), "Dropping YellowHerb");
                actions.dropItem(Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                        Prop.ROOT_DIR + "Loot\\YellowHerb\\");
            }
            TIMER_CHECK_OVERWEIGHT.set(0);
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
