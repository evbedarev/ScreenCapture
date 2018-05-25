package logic;

import checks.YunField11;
import logic.kill_monster.Goat;
import logic.take_loot.*;

public class LogicYunField11 extends Location {

    public LogicYunField11(int threadId) throws Exception {
        super(threadId);
        super.verifyMap =  new YunField11();
        super.killMonster = new Goat();
        super.loot = new TakeLoot[] {
//                new AntelopeHorn(),
                new AntelopeSkin(),
//                new BlueHerb(),
//                new Bottle()
        };
    }

    @Override
    public void createThread() throws Exception {
        Thread thread = new LogicYunField11(1);
        thread.start();
        this.start();
    }

    @Override
    public void mainHandle() throws Exception {

        if (threadId == 0) {
//            locationCheck();
//            findAndKill();
//            checkHP.checkHp();
            pickUpLoot();
//            teleport();
            Thread.sleep(500);

            if (atomicInt.get() > 60) {
                System.out.println("60");
                atomicInt.set(0);
            }
            count++;
        }

        if (threadId == 1) {
            atomicInt.incrementAndGet();
            sleep(1000);
        }
    }


    @Override
    void duringTheFight() throws Exception {
        int atk = 1;
        while (attack.killOrNot()) {
            atk++;
            checkMyHp();
            Thread.sleep(500);
            if (atk > 20) {
                stepAside();
                findAndKill();
            }
        }
    }
}
