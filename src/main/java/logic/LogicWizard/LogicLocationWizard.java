package logic.LogicWizard;

import actions.Actions;
import actions.SleepTime;
import checks.CheckAgressorIsNear;
import checks.CheckSP;
import checks.LocationCheck;
import checks.afterDeath.AfterDeath;
import checks.check_hp.CheckHP;
import logger.LoggerSingle;
import logic.Logic;
import logic.attacks.Attack;
import logic.kill_monster.KillMonster;
import logic.take_loot.LootAround;
import logic.take_loot.TakeLoot;
import main.Prop;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class LogicLocationWizard extends Thread implements Logic {
    static int countOfAttacks = 100;
    static List<KillMonster> killMonsterList;
    static TakeLoot[] loot;
    static TakeLoot[] usefulLoot;
    static AfterDeath checkDie = Prop.checkDie;
    static final CheckHP checkHP = CheckHP.instance();
    static final CheckSP checkSP = CheckSP.instance();
    static final CheckAgressorIsNear checkAgressorIsNear = CheckAgressorIsNear.instance();
    int count = 0;
    static Attack attack;
    static final AtomicInteger ATTACK_TIMER = new AtomicInteger(0);
    static final AtomicInteger ATTACK_MOBS_BEHIND_WALLS = new AtomicInteger(0);
    static Actions actions;
    static LocationCheck locationCheck;
    static LootAround lootAround = LootAround.getInstance();

    public abstract void createThread() throws Exception;

    public void run() {
        try {
            checkHP.initialize(true, Prop.checkHitPoints);
            actions = Actions.instance();
            actions.initialize(loot, usefulLoot);
            checkSP.initialize();
            while (true) {
                mainHandle();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    void duringTheFight() throws Exception {
        ATTACK_TIMER.set(0);
        while (attack.kill()) {
            count = 0;
            checkMyHp();
            SleepTime.sleep(1000);
            if (ATTACK_TIMER.incrementAndGet() > Prop.ATTACK_TIMER) break;
        }
    }

    void findAndKill(KillMonster monster) {
        try {
            ATTACK_MOBS_BEHIND_WALLS.set(0);
            while (monster.kill()) {
//                attackBySwordOrSpell(monster);
//                SleepTime.sleep(8000);
                count = 0;
                checkMyHp();
                actions.pickUpLoot(locationCheck);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    void attackBySwordOrSpell(KillMonster monster) throws Exception{
        SleepTime.sleep(2000);
        killMonstersAround(monster);
    }

    void killMonstersAround(KillMonster monster) throws Exception {
        while(monster.findAndKillAround()) {
//            checkMyHp();
            SleepTime.sleep(2000);
            LoggerSingle.logInfo("LogicLocation.killMonstersAround",
                    "Find monster around, killing");
            checkMyHp();
        }
    }

    public void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    public abstract void mainHandle() throws Exception;
    abstract void runFromMonster() throws Exception;
    abstract void teleport() throws Exception;





}
