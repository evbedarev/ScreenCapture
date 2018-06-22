package logic;

import actions.Actions;
import checks.LocationCheck;
import logic.kill_monster.Attack;
import logic.kill_monster.Attack2;
import logic.kill_monster.KillMonster;
import org.apache.log4j.Logger;

import java.util.List;


public abstract class LogicLocation extends Thread implements Logic {
    static int countOfAttacks = 0;
    static List<KillMonster> killMonsterList;
    int count = 0;
    Attack attack;
    Attack2 attack2;
    Logger logger = Logger.getLogger(this.getClass());
    Actions actions;
    LocationCheck locationCheck;

    public void createThread() throws Exception {
        Thread thread = new LogicGefField11(1);
        thread.start();
        start();
    }

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
        int atk = 1;
        while (attack.killOrNot() || attack2.killOrNot()) {
            count = 0;
            logger.debug("Set count to " + count);
            atk++;
            checkMyHp();
            Thread.sleep(1000);
            if (atk > countOfAttacks) {
                actions.stepAside(locationCheck);
                killMonsterList.forEach(this::findAndKill);
                atk=1;
            }
        }
    }

    void findAndKill(KillMonster monster) {
        try {
            while (monster.kill()) {
                count = 0;
                runFromMonster();
                logger.debug("Set count to " + count);
                checkMyHp();
                Thread.sleep(500);
                duringTheFight();
            }
            if (count == 0)
                actions.stepAside(locationCheck);

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
