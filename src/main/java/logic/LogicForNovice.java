//package logic;
//
//import checks.CheckHP;
//import checks.location.GefField05;
//import checks.location.VerifyMap;
//import email.MsgLocationChanged;
//import email.SendMessage;
//import key_and_mouse.Keys;
//import key_and_mouse.Mouse;
//import logic.kill_monster.*;
//
//import logic.take_loot.*;
//import org.apache.log4j.LoggerSingle;
//
//import java.awt.event.KeyEvent;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class LogicForNovice extends Thread{
//    private int count = 0;
//    private int countForSendMsg = 0;
//    private int threadId;
//    private final Mouse mouse = new Mouse();
//    private final CheckHP checkHP = new CheckHP();
//    private final static AtomicInteger atomicInt = new AtomicInteger(0);
//    private VerifyMap verifyMap = new GefField05();
//    private SendMessage sendMessage = new SendMessage();
//    private Keys keys;
//    private Attack attack;
//
//    public LogicForNovice(int threadId) throws Exception {
//        this.threadId = threadId;
//        keys = new Keys();
//        attack = new Attack();
//    }
//
//    public void createThread() throws Exception {
//        for (int i=0; i < 2; i++) {
//            Thread thread = new LogicForNovice(i);
//            thread.start();
//        }
//    }
//
//    @Override
//    public void run() {
//        try {
//
//            while (true) {
//                   mainHandle();
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    private void mainHandle() throws Exception {
//
//        if (threadId == 0) {
//
//            while (!verifyMap.onDesiredLocation()) {
//                KillMonster goToWarp = new Warp();
//                System.out.println("Нахожусь не на карте!!");
//                goToWarp.kill();
//                sleep(2000);
//                teleport();
//                countForSendMsg++;
//                if (countForSendMsg == 100) {
//                    sendMessage.send(new MsgLocationChanged());
//                }
//            }
//            countForSendMsg = 0;
//
//    //      KillMonster creamy = new Poring();
////            KillMonster killMark = new Creamy();
//            KillMonster creamy = new Goblin();
//            KillMonster awareMonster = new GoblinLeader();
//
//            while (creamy.kill()) {
//                if (awareMonster.kill()) {
//                    System.out.println("GOBLIN LEADER");
//                    teleport();
//                }
//
//                SleepTime.sleep(1000);
//                duringTheFight();
//            }
//
//            count++;
//            stepAside();
//            checkHP.needHeal();
//            pickUpLoot();
//
//            if (count > 2) {
//                teleport();
//            }
//            SleepTime.sleep(2000);
//            if (atomicInt.get() > 60) {
//                System.out.println("60");
//                atomicInt.set(0);
//            }
//        }
//
//        if (threadId == 1) {
//            atomicInt.incrementAndGet();
//            sleep(1000);
//        }
//    }
//
//    private void checkMyHp() throws Exception {
//        checkHP.checkHp();
//        pickUpCard();
//        SleepTime.sleep(1000);
//    }
//
//    private void duringTheFight() throws Exception {
//        int atk = 1;
//            count = 0;
//        while (attack.killOrNot()) {
//            atk++;
//            checkMyHp();
//            SleepTime.sleep(500);
//            if (atk > 20) {
//                break;
//            }
//        }
//    }
//
//    private void pickUpLoot() throws Exception {
//        TakeLoot powderOfButterfly = new PowderOfButterfly();
//        TakeLoot honey = new Honey();
//        pickUpCard();
//        powderOfButterfly.pickUp();
//        honey.pickUp();
//    }
//
//    private void pickUpCard() throws Exception {
//        TakeLoot card = new Card();
//        TakeLoot clothes = new Clothes();
//        TakeLoot shield = new Shield();
//        TakeLoot mask = new Mask();
//        card.pickUp();
//        clothes.pickUp();
//        shield.pickUp();
//        mask.pickUp();
//    }
//
//    private void stepAside() throws Exception {
//        double t = 2 * Math.PI * Math.random();
//        double minRadius = 75;
//        double maxRadius = 150;
//
//        double x = minRadius * Math.cos(t);
////        double y = minRadius * Math.sin(t);
//
//        double x1 = maxRadius * Math.cos(t);
////        double y1 = maxRadius * Math.sin(t);
//
//        double mediumX = x + Math.random()*(x1 - x);
//        double mediumR = mediumX/Math.cos(t);
//        double mediumY = mediumR * Math.sin(t);
//
//        mouse.mouseClick(800 + (int) Math.round(mediumX),
//                450 + (int) Math.round(mediumY));
//    }
//
//    private void teleport() throws InterruptedException {
//        keys.keyPress(KeyEvent.VK_F2);
//        SleepTime.sleep(1000);
//        keys.keyPress(KeyEvent.VK_ENTER);
//        count = 1;
//    }
//
//}
//
