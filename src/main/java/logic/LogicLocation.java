package logic;

import actions.Actions;
import actions.SleepTime;
import checks.*;
import checks.afterDeath.AfterDeath;
import checks.check_hp.CheckHP;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.attacks.Attack;
import logic.kill_monster.*;
import logic.move_by_card.MoveByCard;
import logic.screen_shot.ScreenShot;
import logic.take_loot.LootAround;
import logic.take_loot.TakeLoot;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class LogicLocation extends Thread implements Logic {
    static int countStartProgram = 0;
    static int countOfAttacks = 100;
    static public List<KillMonster> killMonsterList;
    static TakeLoot[] loot;
    static TakeLoot[] usefulLoot;
    static AfterDeath checkDie = Prop.checkDie;
    static final CheckHP checkHP = CheckHP.instance();
    static final CheckSP checkSP = CheckSP.instance();
    static final CheckAgressorIsNear checkAgressorIsNear = CheckAgressorIsNear.instance();
    int count = 0;
    static Attack attack;
    static final AtomicInteger ATTACK_TIMER = new AtomicInteger(0);
    static final AtomicInteger ATTACK_MOBS_BEHIND_WALLS = new AtomicInteger(0);
    static Actions actions;
    static LocationCheck locationCheck;
    static LootAround lootAround = LootAround.getInstance();
    static MoveByCard moveByCard;
    private Keys keys;
    private Mouse mouse;
    private int cntAttack;
    private static List<int[]> coordsArround = new ArrayList<>();

    public abstract void createThread() throws Exception;

    public void run() {
        try {
            checkHP.initialize(true, Prop.checkHitPoints);
            actions = Actions.instance();
            actions.initialize(loot, usefulLoot);
            checkSP.initialize();
            mouse = Mouse.getInstance();
            feelListCoordsArround();
            while (true) {
                mainHandle();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    void duringTheFight() throws Exception {
        ATTACK_TIMER.set(0);
        while (attack.kill()) {
            count = 0;
            checkMyHp();
            SleepTime.sleep(500);
            if (ATTACK_TIMER.incrementAndGet() > Prop.ATTACK_TIMER) break;
        }
    }

    public void findAndKill(KillMonster monster) {
        try {
            boolean wasAttack = false;
            cntAttack = 0;
            while (monster.kill()) {
                SleepTime.sleep(200);
//                attackBySwordOrSpell(monster);
                findMonsterArround();
                SleepTime.sleep(200);
                if (cntAttack > 6) {
                    actions.useWing();
                    SleepTime.sleep(500);
                    cntAttack = 0;
                    LoggerSingle.logInfo("LogicLocation.findAndKill",
                            "use wing. can't walk to the monster");
                    break;
                }
                cntAttack++;
                wasAttack = true;
            }
            if (wasAttack) {
                actions.stepAside(new int[]{50, 100});
                actions.pickUpCard();
                actions.pickUpLoot(locationCheck);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void findAndKillWithoutTakingLoot() {
        try {
            for (KillMonster monster: killMonsterList) {
                cntAttack = 0;
                while (monster.kill()) {
                    SleepTime.sleep(200);
                    attackBySwordOrSpell(monster);
                    SleepTime.sleep(200);
                    if (cntAttack > 4) {
                        actions.useWing();
                        SleepTime.sleep(500);
                        cntAttack = 0;
                        LoggerSingle.logInfo("LogicLocation.findAndKill",
                                "use wing. can't walk to the monster");
                        break;
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    void attackBySwordOrSpell(KillMonster monster) throws Exception{
        if (Prop.NEED_SPELL_ATTACK && CheckSP.enoughSP) {
            checkMyHp();
            killMonstersAround(monster);
        } else {
//            checkMyHp();
//            SleepTime.sleep(200);
//            duringTheFight();
            SleepTime.sleep(300);
            if (!killMonstersAround(monster)) {
                System.out.println("return false");
                cntAttack++;
            } else {
                System.out.println("return true");
                cntAttack = 0;
            }
        }
    }

    boolean killMonstersAround(KillMonster monster) throws Exception {
        int countAttack = 0;
        keys = Keys.getInstance();
        boolean killAll = false;
        while(monster.findAndKillAround() ) {
            duringTheFight();
            countAttack++;
            checkMyHp();
            //change
            SleepTime.sleep(550);
            LoggerSingle.logInfo("LogicLocation.killMonstersAround",
                    "Find monster around, killing");
            killAll = true;
            if (countAttack > 20) return false;
        }
        return killAll;
    }

    public void findMonsterArround() throws Exception {
        SleepTime.sleep(1000);
        for (int[] ints : coordsArround) {
            mouse.mouseMove(ints[0], ints[1]);
            checkToAttack(ints[0], ints[1]);
        }
        actions.stepAside(new int[]{50, 100});
    }

    public void checkToAttack(int x, int y) throws Exception {
        boolean isAttackC = true;
        int count = 0;
        SleepTime.sleep(100);
        while (isAttackC) {
            count++;
            BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
            int rgb = image.getRGB(x + 21, y + 16);
            if (rgb > -9500000 && rgb < -9200000) {
                mouse.leftClick();
                SleepTime.sleep(1500);
                checkMyHp();
            } else {
                isAttackC = false;
            }
            SleepTime.sleep(100);
            if (count > 30)
                break;
        }
    }


    public void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    public void checkMyHp(BufferedImage image) throws Exception {
        checkHP.checkHp(image);
    }

    private void feelListCoordsArround() {
        coordsArround.add(new int[] {810,410});
        coordsArround.add(new int[] {810,367});
        coordsArround.add(new int[] {845,390});
        coordsArround.add(new int[] {855,445});
        coordsArround.add(new int[] {845,495});
        coordsArround.add(new int[] {810,492});
        coordsArround.add(new int[] {770,482});
        coordsArround.add(new int[] {760,450});
        coordsArround.add(new int[] {770,412});
    }
    public BufferedImage takeScreenShot() {
        return Prop.context.getBean(ScreenShot.class).pop();
    }
    public abstract void mainHandle() throws Exception;
    abstract void runFromMonster() throws Exception;
    abstract void teleport() throws Exception;
    public LocationCheck getLocationCheck() {
        return locationCheck;
    }

}
