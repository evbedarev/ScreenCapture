package logic;

import checks.GefField05;
import logic.kill_monster.*;

public class LogicGefField05 extends Location {

    public LogicGefField05(int threadId) throws Exception {
        super(threadId);
        super.verifyMap = new GefField05();
    }

    @Override
    public void createThread() throws Exception {
        this.start();
    }

    @Override
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

    @Override
    void checkMyHp() throws Exception {
        checkHP.needHeal();
        pickUpCard();
        Thread.sleep(1000);
    }
}