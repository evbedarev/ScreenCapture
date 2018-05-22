package logic;

import email.MsgLocationChanged;
import logic.kill_monster.Goblin;
import logic.kill_monster.GoblinLeader;
import logic.kill_monster.KillMonster;

public class LogicGefField11 extends Location {
    public LogicGefField11(int threadId) throws Exception {
        super(threadId);
    }

    public void mainHandle() throws Exception {

        if (threadId == 0) {
            locationCheck();
            KillMonster killMonster = new Goblin();
            KillMonster awareMonster = new GoblinLeader();

            while (killMonster.findAndKill()) {
                if (awareMonster.findAndKill()) {
                    System.out.println("GOBLIN LEADER");
                    teleport();
                }

                Thread.sleep(1000);
                duringTheFight();
            }

            count++;
            stepAside();
            checkHP.needHeal();
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
