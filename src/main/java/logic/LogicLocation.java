package logic;

import actions.Actions;
import checks.LocationCheck;
import logic.kill_monster.Attack;
import logic.kill_monster.Attack2;
import org.apache.log4j.Logger;

public abstract class LogicLocation extends Thread implements Logic {
    public final static int COUNT_OF_ATTACKS = 0;
    private int count = 0;
    private Thread logicLocation;
    private Attack attack;
    private Attack2 attack2;
    private Logger logger = Logger.getLogger(this.getClass());
    private Actions actions;
    private LocationCheck locationCheck;

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
            if (atk > COUNT_OF_ATTACKS) {
                actions.stepAside(locationCheck);
                findAndKill();
                atk=1;
            }
        }
    }

    public abstract void mainHandle() throws Exception;

    abstract void checkCast() throws InterruptedException;
    abstract void runFromMonster() throws Exception;
    abstract void findAndKill() throws Exception;
    abstract void checkMyHp() throws Exception;
    abstract void teleport() throws Exception;





}
