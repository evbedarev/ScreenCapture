//package logic;
//
//import checks.CheckHP;
//import checks.location.IzludDun03;
//import checks.location.VerifyMap;
//import email.MsgLocationChanged;
//import email.SendMessage;
//import key_and_mouse.Keys;
//import key_and_mouse.Mouse;
//import logic.kill_monster.*;
//import logic.take_loot.*;
//import org.apache.log4j.LoggerSingle;
//
//import java.awt.event.KeyEvent;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class LogicIzludDun03 extends Thread implements Logic {
//    private int count = 0;
//    private int countForSendMsg = 0;
//    private final int threadId;
//    private final Mouse mouse = new Mouse();
//    private final CheckHP checkHP = new CheckHP(true);
//    private final static AtomicInteger ATOMIC_INT = new AtomicInteger(0);
//    private LoggerSingle logger = LoggerSingle.getLogger(this.getClass());
//
//    private final VerifyMap verifyMap;
//    private final SendMessage sendMessage = new SendMessage();
//    private final Keys keys;
//    private final Attack attack;
//    private final KillMonster killMark;
//    private final KillMonster killSwordfish;
//    private final KillMonster killPhen;
//    private final KillMonster killMerman;
//
//    private final TakeLoot[] usefulLoot;
//    private final TakeLoot[] loot;
//
//
//    public LogicIzludDun03(int threadId) throws Exception {
//        System.out.println(threadId);
//        verifyMap =  new IzludDun03();
//        killMark = new Mark(logger);
//        killPhen = new Phen(logger);
//        killMerman = new Merman(logger);
//        killSwordfish = new Swordfish(logger);
//
//        loot = new TakeLoot[] {
////                new AntelopeHorn(),
//                new AntelopeSkin(logger),
//                new BlueHerb(logger),
//                new Bottle(logger)
//        };
//
//        usefulLoot = new TakeLoot[] {
//                new Card(logger),
//                new Card1(logger)
////            new Clothes(),
////            new Shield(),
////            new Mask()
//        };
//
//        keys = new Keys();
//        attack = new Attack(logger);
//        this.threadId = threadId;
//    }
//
//    public void createThread() throws Exception {
////        Thread thread = new LogicYunField11(1);
////        thread.start();
//        this.start();
//    }
//
//    public void run() {
//        while (true) {
//            try {
//                mainHandle();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        }
//    }
//
//    private void mainHandle() throws Exception {
//
//        if (threadId == 0) {
//            locationCheck();
//            if (count == 0)
//                stepAside();
//            findAndKill();
//            checkHP.checkHp();
//            pickUpLoot();
//            teleport();
//            count++;
//            logger.debug("Incrase count by 1, count=" + count);
//        }
//
//        if (threadId == 1) {
//            ATOMIC_INT.incrementAndGet();
//            sleep(1000);
//        }
//    }
//
//    void checkMyHp() throws Exception {
//        checkHP.checkHp();
//        pickUpCard();
//    }
//
//    void findAndKill() throws Exception{
//        while (killSwordfish.kill() ||
//                killMerman.kill() ||
//                killMark.kill() ||
//                killPhen.kill()) {
//            count = 0;
//            logger.debug("Set count to " + count);
//            SleepTime.sleep(3000);
//            duringTheFight();
//        }
//        if (count == 0)
//            stepAside();
//    }
//
//    void duringTheFight() throws Exception {
//        int atk = 1;
//        while (attack.killOrNot()) {
//            count = 0;
//            logger.debug("Set count to " + count);
//            atk++;
//            checkMyHp();
//            SleepTime.sleep(500);
//            if (atk > 100) {
//                stepAside();
//                findAndKill();
//                atk=1;
//            }
//        }
//    }
//
//    void pickUpLoot() throws Exception {
//        pickUpCard();
//        for (TakeLoot takeLoot: loot) {
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
//    /**
//     * Вычисляет рэндомную точку между двух окружностей вокруг центра,
//     * куда будет отходить перс
//     * @throws Exception
//     */
//    private void stepAside() throws Exception {
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
//        sleep(1000);
//    }
//
//    private void teleport() throws Exception {
//        if (count > 20) {
//            logger.info("TELEPORTING count=" + count);
//            count = 0;
//            logger.debug("Set count to " + count);
//            keys.keyPress(KeyEvent.VK_F2);
//            SleepTime.sleep(1000);
//            keys.keyPress(KeyEvent.VK_ENTER);
//            SleepTime.sleep(1000);
//            stepAside();
//        }
//    }
//
//    private void locationCheck() throws Exception {
//        while (!verifyMap.onDesiredLocation()) {
//            sleep(5000);
//            KillMonster goToWarp = new Warp(logger);
//            logger.info("Нахожусь не на карте!!");
//            goToWarp.kill();
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
//}
