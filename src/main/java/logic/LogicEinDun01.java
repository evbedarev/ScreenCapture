package logic;

import actions.SleepTime;
import checks.CheckSP;
import checks.LocationCheck;
import checks.location.EinDun01;
import checks.location.YunField04;
import logger.LoggerSingle;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun04;
import logic.kill_monster.*;
import logic.take_loot.*;
import main.Prop;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicEinDun01 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicEinDun01(int threadId) throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        locationCheck = new LocationCheck(new EinDun01());
        lootAround.initialize(new HandYun04());
        killMonsterList = Stream
                .of(new Pitman(), new Porcellio(), new Noxous() )
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new GunPowder(),
                new Steel()
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Shield(),
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
        checkSP.enoghtSP();
        killMonsterList.forEach(this::findAndKill);
        checkMyHp();
        actions.pickUpCard();
        actions.pickUpLoot();
        teleport();
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
            Prop.cast.cast();
            actions.teleport(locationCheck);
//            actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }

    void runFromMonster() throws Exception {
    }

    @Override
    void attackBySwordOrSpell(KillMonster monster) throws Exception{
        if (Prop.NEED_SPELL_ATTACK && CheckSP.enoughSP) {
            checkMyHp();
            killMonstersAround(monster);
        } else {
            checkMyHp();
            SleepTime.sleep(1000);
            duringTheFight();
            killMonstersAround(monster);
            if (ATTACK_MOBS_BEHIND_WALLS.get() > Prop.ATTACK_MOBS_BEHIND_WALLS) {
                actions.teleport();
                LoggerSingle.logInfo("LogicLocation.attackBySwordOrSpell",
                        "teleporting. Mobs behind the walls");
            }
        }
        Prop.cast.cast();
        lootAround.takeLootAround();
    }

}
