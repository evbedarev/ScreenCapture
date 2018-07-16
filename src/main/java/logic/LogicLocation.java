package logic;

import actions.Actions;
import checks.CheckDie;
import checks.CheckHP;
import checks.CheckSP;
import checks.LocationCheck;
import logic.attacks.Attack;
import logic.kill_monster.*;
import logic.take_loot.LootAround;
import logic.take_loot.TakeLoot;
import main.Prop;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public abstract class LogicLocation extends Thread implements Logic {
    static int countOfAttacks = 100;
    static List<KillMonster> killMonsterList;
    static TakeLoot[] loot;
    static TakeLoot[] usefulLoot;
    static CheckDie checkDie = CheckDie.instance();
    final CheckHP checkHP = CheckHP.instance();
    final CheckSP checkSP = CheckSP.instance();
    int count = 0;
    static Attack attack;
    final static AtomicInteger ATTACK_TIMER = new AtomicInteger(0);
    final static AtomicInteger ATTACK_MOBS_BEHIND_WALLS = new AtomicInteger(0);
    Logger logger = Logger.getLogger(this.getClass());
    static Actions actions;
    static LocationCheck locationCheck;
    static LootAround lootAround;

    public abstract void createThread() throws Exception;

    public void run() {
        try {
            checkHP.initialize(true, locationCheck);
            checkDie.initialize();
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
            logger.debug("Set count to " + count);
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
//                    count = 0;
//                    logger.debug("Set count to " + count);
//                    checkMyHp();
//                    Thread.sleep(500);
//                    duringTheFight();
                    killMonstersAround(monster);
//                    cnt = 0;
//                    if (ATTACK_MOBS_BEHIND_WALLS.get() > Prop.ATTACK_MOBS_BEHIND_WALLS) {
//                        actions.teleport();
//                        logger.info("LogicLocation.findAndKill: teleporting. Mobs behind the walls");
//                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    boolean killMonstersAround(KillMonster monster) throws Exception {
        boolean monstersAround = false;

        while(checkMonstersAround(monster)) {
            logger.info("LogicLocation.killMonstersAround: Find monster arround, killing ");
            duringTheFight();
            checkMyHp();
            monstersAround = true;
        }

        if (monstersAround) {
            logger.info("LogicLocation.killMonstersAround: Pick up loot arround");
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
    abstract void checkCast() throws InterruptedException;
    abstract void runFromMonster() throws Exception;
    abstract void checkMyHp() throws Exception;
    abstract void teleport() throws Exception;





}
