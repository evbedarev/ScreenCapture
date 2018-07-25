package logic;

import actions.Actions;
import checks.*;
import checks.afterDeath.AfterDeath;
import logger.LoggerSingle;
import logic.attacks.Attack;
import logic.kill_monster.*;
import logic.take_loot.LootAround;
import logic.take_loot.TakeLoot;
import main.Prop;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class LogicLocation extends Thread implements Logic {
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
            checkHP.initialize(true, locationCheck);
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
            Thread.sleep(500);
            if (ATTACK_TIMER.incrementAndGet() > Prop.ATTACK_TIMER) break;
        }
    }

    void findAndKill(KillMonster monster) {
        try {
            for (int  cnt=0; cnt < Prop.COUNT_OF_CHECKS_MONSTER; cnt++) {
                ATTACK_MOBS_BEHIND_WALLS.set(0);
                while (monster.kill()) {
                    attackBySwordOrSpell(monster);
                    cnt = 0;
                    count = 0;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void attackBySwordOrSpell(KillMonster monster) throws Exception{
        if (Prop.NEED_SPELL_ATTACK && CheckSP.enoughSP) {
            checkMyHp();
            killMonstersAround(monster);
        } else {
            checkMyHp();
            Thread.sleep(500);
            duringTheFight();
            killMonstersAround(monster);
            if (ATTACK_MOBS_BEHIND_WALLS.get() > Prop.ATTACK_MOBS_BEHIND_WALLS) {
                actions.teleport();
                LoggerSingle.logInfo("LogicLocation.attackBySwordOrSpell",
                        "teleporting. Mobs behind the walls");
            }
        }
    }

    boolean killMonstersAround(KillMonster monster) throws Exception {
        boolean monstersAround = false;

        while(checkMonstersAround(monster)) {
            LoggerSingle.logInfo("LogicLocation.killMonstersAround",
                    "Find monster around, killing");
            duringTheFight();
            checkMyHp();
            monstersAround = true;
        }

        if (monstersAround) {
            LoggerSingle.logInfo("LogicLocation.killMonstersAround",
                    "Pick up loot around");
            lootAround.takeLootAround();
            return false;
        }
        return true;
    }

    boolean checkMonstersAround(KillMonster monster) throws Exception {
        for (int cnt = 0; cnt <  Prop.COUNT_OF_CHECKS_MONSTER; cnt++) {
            if (monster.killAround()) return true;
        }
        return false;
    }


    public abstract void mainHandle() throws Exception;
    abstract void checkCast() throws Exception;
    abstract void runFromMonster() throws Exception;
    abstract void checkMyHp() throws Exception;
    abstract void teleport() throws Exception;





}
