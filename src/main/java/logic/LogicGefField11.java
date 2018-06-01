//package logic;
//
//import checks.GefField11;
//import logic.kill_monster.Goblin;
//import logic.kill_monster.GoblinLeader;
//
//public class LogicGefField11 extends Location {
//    public LogicGefField11(int threadId) throws Exception {
//        super(threadId);
//        super.verifyMap =  new GefField11();
//        super.awareMonster = new GoblinLeader();
//        super.creamy = new Goblin();
//    }
//
//    @Override
//    public void createThread() throws Exception {
//        Thread thread = new LogicGefField11(1);
//        thread.start();
//        this.start();
//    }
//
//    @Override
//    public void mainHandle() throws Exception {
//
//        if (threadId == 0) {
//            locationCheck();
//            findAndKill();
//            checkHP.checkHp();
//            pickUpLoot();
//            teleport();
//            Thread.sleep(2000);
//
//            if (atomicInt.get() > 60) {
//                System.out.println("60");
//                atomicInt.set(0);
//            }
//            count++;
//        }
//
//        if (threadId == 1) {
//            atomicInt.incrementAndGet();
//            sleep(1000);
//        }
//    }
//
//
//    @Override
//    void duringTheFight() throws Exception {
//        int atk = 1;
//        while (attack.killOrNot()) {
//            atk++;
//            checkMyHp();
//            Thread.sleep(500);
//            if (atk > 20) {
//                stepAside();
//                findAndKill();
//            }
//        }
//    }
//}
