package logic;

import checks.GefField11;
import logic.kill_monster.Goblin;
import logic.kill_monster.GoblinLeader;
import logic.kill_monster.KillMonster;

import java.awt.event.KeyEvent;

public class LogicGefField11 extends Location {
    KillMonster killMonster;
    public LogicGefField11(int threadId) throws Exception {
        super(threadId);
        super.verifyMap =  new GefField11();
        super.awareMonster = new GoblinLeader();
        killMonster = new Goblin();
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
            findAndKill();
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
            count++;
        }

        if (threadId == 1) {
            atomicInt.incrementAndGet();
            sleep(1000);
        }
    }

    void findAndKill() throws Exception{
        awareMonster();
        while (killMonster.findAndKill()) {
            count = 0;
            awareMonster();
            Thread.sleep(1000);
            duringTheFight();
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

    @Override
    void teleport() throws Exception {
        findAndKill();
        pickUpLoot();
        keys.keyPress(KeyEvent.VK_F2);
        Thread.sleep(1000);
        keys.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        count = 0;
    }

}
