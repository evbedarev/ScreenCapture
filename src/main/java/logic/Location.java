//package logic;
//
//import checks.check_hp.CheckHP;
//import checks.location.VerifyMap;
//import email.MsgLocationChanged;
//import email.SendMessage;
//import key_and_mouse.Keys;
//import key_and_mouse.Mouse;
//import logic.attacks.Attack;
//import logic.kill_monster.KillMonster;
//import logic.kill_monster.Warp;
//import logic.take_loot.*;
//import org.apache.log4j.LoggerSingle;
//
//import java.awt.event.KeyEvent;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class Location extends Thread implements Logic {
//    int count = 0;
//    int countForSendMsg = 0;
//    int threadId;
//    final Mouse mouse = new Mouse();
//    final CheckHP checkHP = new CheckHP();
//    final static AtomicInteger atomicInt = new AtomicInteger(0);
//    private final LoggerSingle logger = LoggerSingle.getLogger(this.getClass());
//
//    VerifyMap verifyMap;
//    SendMessage sendMessage = new SendMessage();
//    Keys keys;
//    Attack attack;
//    KillMonster awareMonster;
//    KillMonster creamy;
//
//    TakeLoot[] usefulLoot = new TakeLoot[] {
//            new Card(),
//            new Clothes(),
//            new Shield(),
//            new Mask()
//    };
//    TakeLoot[] loot = new TakeLoot[] {
//            new PowderOfButterfly(),
//            new Honey()
//    };
//
//    Location(int threadId) throws Exception {
//        this.threadId = threadId;
//        keys = new Keys();
//        attack = new Attack();
//    }
//
//    @Override
//    public void createThread() throws Exception {
//        System.out.println(threadId);
////        Thread thread = new Location(1);
////        thread.start();
////        this.start();
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                mainHandle();
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    public void mainHandle() throws Exception {
//        System.out.println("qq");
//    }
//
//    public void checkMyHp() throws Exception {
//        checkHP.checkHp();
////        pickUpCard();
//    }
//
//    void duringTheFight() throws Exception {
//        int atk = 1;
//        while (attack.killOrNot()) {
//            atk++;
//            checkMyHp();
//            SleepTime.sleep(500);
//            if (atk > 20) {
//                stepAside();
//                atk = 1;
//            }
//        }
//        pickUpLoot();
//    }
//
//    void pickUpLoot() throws Exception {
////        pickUpCard();
//        for (TakeLoot takeLoot: loot) {
////            if (takeLoot.takeLoot())
////                count = 0;
//            takeLoot.pickUp();
//        }
//    }
//
//    void pickUpCard() throws Exception {
//        for (TakeLoot takeLoot: usefulLoot) {
//            takeLoot.pickUp();
//        }
//    }
//
//    void stepAside() throws Exception {
//        double t = 2 * Math.PI * Math.random();
//        double minRadius = 75;
//        double maxRadius = 150;
//
//        double x = minRadius * Math.cos(t);
//        double x1 = maxRadius * Math.cos(t);
//
//        double mediumX = x + Math.random()*(x1 - x);
//        double mediumR = mediumX/Math.cos(t);
//        double mediumY = mediumR * Math.sin(t);
//
//        mouse.mouseClick(800 + (int) Math.round(mediumX),
//                450 + (int) Math.round(mediumY));
//    }
//
//    void teleport() throws Exception {
//        if (count > 3) {
//            keys.keyPress(KeyEvent.VK_F2);
//            SleepTime.sleep(1000);
//            keys.keyPress(KeyEvent.VK_ENTER);
//            SleepTime.sleep(1000);
//            stepAside();
//            logger.info("TELEPORTING count=" + count);
//            count = 0;
//        }
//    }
//
//    void findAndKill() throws Exception{
////        awareMonster();
//        while (creamy.findAndKill()) {
//            count = 0;
////            awareMonster();
//            SleepTime.sleep(3000);
//            duringTheFight();
//        }
//    }
//
//    void locationCheck() throws Exception {
//        while (!verifyMap.onDesiredLocation()) {
//            sleep(5000);
//            KillMonster goToWarp = new Warp();
//            logger.info("Нахожусь не на карте!!");
//            goToWarp.findAndKill();
//            sleep(2000);
//            if (verifyMap.onDesiredLocation()) {
//                teleport();
//                sleep(2000);
//            }
//            countForSendMsg++;
//            if (countForSendMsg == 100) {
//                sendMessage.send(new MsgLocationChanged());
//            }
//        }
//        countForSendMsg = 0;
//    }
//
//    void awareMonster() throws Exception {
//        if (awareMonster != null && awareMonster.findAndKill()) {
//            logger.info("GOBLIN LEADER, TELEPORT");
//            teleport();
//        }
//    }
//
//
//}
