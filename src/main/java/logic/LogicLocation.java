package logic;

import actions.Actions;
import checks.LocationCheck;
import logic.kill_monster.*;
import logic.take_loot.LootAround;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public abstract class LogicLocation extends Thread implements Logic {
    static int countOfAttacks = 100;
    static List<KillMonster> killMonsterList;
    int count = 0;
    static Attack attack;
    final static AtomicInteger ATTACK_TIMER = new AtomicInteger(0);
    Logger logger = Logger.getLogger(this.getClass());
    Actions actions;
    LocationCheck locationCheck;
    LootAround lootAround;

    public abstract void createThread() throws Exception;

    public void run() {
        try {
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
            if (ATTACK_TIMER.incrementAndGet() > 10) break;
        }
    }

    void findAndKill(KillMonster monster) {
        try {
            for (int  cnt=0; cnt < 5; cnt++)
                while (monster.kill()) {
                    count = 0;
                    runFromMonster();
                    logger.debug("Set count to " + count);
                    checkMyHp();
                    Thread.sleep(1000);
                    duringTheFight();
                    cnt = 0;
                }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public abstract void mainHandle() throws Exception;
    abstract void checkCast() throws InterruptedException;
    abstract void runFromMonster() throws Exception;
    abstract void checkMyHp() throws Exception;
    abstract void teleport() throws Exception;





}
