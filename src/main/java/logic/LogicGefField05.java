package logic;

import checks.GefField05;
import checks.GefField11;
import checks.VerifyMap;
import logic.kill_monster.*;

public class LogicGefField05 extends Location {
    VerifyMap verifyMap = new GefField05();

    public LogicGefField05(int threadId) throws Exception {
        super(threadId);
    }

    public void createThread() throws Exception {
        for (int i=0; i < 2; i++) {
            Thread thread = new LogicGefField05(i);
            thread.start();
        }
    }

    public void mainHandle() throws Exception {
        locationCheck();
        KillMonster killMonster = new ThiefBug();
        KillMonster killMonster1 = new Creamy();

        if (killMonster.findAndKill() || killMonster1.findAndKill()) {
            duringTheFight();
        } else {
            count++;
            Thread.sleep(1000);
            pickUpLoot();
        }
        if (count > 6) {
            teleport();
            count = 3;
        }
    }
}
