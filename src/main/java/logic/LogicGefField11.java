package logic;

import checks.GefField11;
import logic.kill_monster.Goblin;
import logic.kill_monster.GoblinLeader;
import logic.kill_monster.KillMonster;

public class LogicGefField11 extends Location {
    public LogicGefField11(int threadId) throws Exception {
        super(threadId);
        super.verifyMap =  new GefField11();
        super.awareMonster = new GoblinLeader();
    }

    @Override
    public void createThread() throws Exception {
        for (int i=0; i < 2; i++) {
            Thread thread = new LogicGefField11(i);
            thread.start();
        }
    }

    @Override
    public void mainHandle() throws Exception {

        if (threadId == 0) {
            locationCheck();
            KillMonster killMonster = new Goblin();

            awareMonster();
            while (killMonster.findAndKill()) {
                count = 0;
                awareMonster();
                Thread.sleep(1000);
                duringTheFight();
            }

            count++;
            stepAside();
            checkHP.checkHp();
            pickUpLoot();

            if (count > 2) {
                teleport();
            }

            Thread.sleep(2000);
            if (atomicInt.get() > 60) {
                System.out.println("60");
                atomicInt.set(0);
            }
        }

        if (threadId == 1) {
            atomicInt.incrementAndGet();
            sleep(1000);
        }
    }
}
