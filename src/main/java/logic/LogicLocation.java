package logic;

import actions.Actions;
import actions.SleepTime;
import checks.*;
import checks.afterDeath.AfterDeath;
import checks.check_hp.CheckHP;
import find_fragments.FindFragmentFiles;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import logger.LoggerSingle;
import logic.attacks.Attack;
import logic.kill_monster.*;
import logic.move_by_card.MoveByCard;
import logic.take_loot.LootAround;
import logic.take_loot.TakeLoot;
import main.Prop;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class LogicLocation extends Thread implements Logic {
    static int countOfAttacks = 100;
    static List<KillMonster> killMonsterList;
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
    static FindFragmentInImage findFragmentInImage;
    private Keys keys;
    Capture capture;
    BufferedImage screenShot;
    private List<BufferedImage> attackLine;
    private int cntAttack;

    public abstract void createThread() throws Exception;

    public void run() {
        try {
            checkHP.initialize(true, Prop.checkHitPoints);
            actions = Actions.instance();
            actions.initialize(loot, usefulLoot);
            checkSP.initialize();
            capture = Capture.instance();
            FindFragmentFiles findFragmentFiles = FindFragmentFiles.getInstance();
            attackLine = findFragmentFiles.fragments("frag*", Prop.ROOT_DIR + "KillMonsters\\LineHpMonsters\\");
            findFragmentInImage = FindFragmentInImage.getInstance();
            while (true) {
                mainHandle();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    boolean duringTheFight() throws Exception {
        boolean wasAnAttack = false;
        int timerRepeatAttack = 0;
        findFragmentInImage.setScreen(new int[] {0, 1600, 0, 900});
        while (findFragmentInImage.findImage(attackLine).isPresent()) {
            SleepTime.sleep(500);
            System.out.println("Find line in screen");
            wasAnAttack = true;
            checkMyHp(findFragmentInImage.getCurrentScreenShot());
            if (timerRepeatAttack > 40) {
                MoveByCard.wingAway();
                break;
            }
            timerRepeatAttack++;
        }
        return wasAnAttack;
    }

    public void findAndKill(KillMonster monster) {
        try {
            int cntAttemptsAttack = 0;
            boolean wasAttacks = false;
            Prop.cast.cast();
            while (monster.kill()) {
                SleepTime.sleep(500);
                if (!duringTheFight())
                    cntAttemptsAttack++;

                SleepTime.sleep(200);

                if (cntAttemptsAttack > 4) {
                    actions.useWing();
                    SleepTime.sleep(1000);
                    cntAttack = 0;
                    LoggerSingle.logInfo("LogicLocation.findAndKill",
                            "use wing. can't walk to the monster");
                    break;
                }
                wasAttacks = true;
            }
            if (wasAttacks)
                actions.pickUpCard();
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
            duringTheFight();
            SleepTime.sleep(300);
            if (!killMonstersAround(monster)) {
                cntAttack++;
            } else {
                cntAttack = 0;
            }
        }
//        actions.pickUpLoot(locationCheck);
        actions.stepAside(new int[] {50,100});
        actions.pickUpCard();
        actions.pickUpLoot(locationCheck);
    }

    boolean killMonstersAround(KillMonster monster) throws Exception {
        int countAttack = 0;
        keys = Keys.getInstance();
        boolean killAll = false;
        while(monster.findAndKillAround()) {
            duringTheFight();
            countAttack++;
            checkMyHp();
            SleepTime.sleep(500);
            LoggerSingle.logInfo("LogicLocation.killMonstersAround",
                    "Find monster around, killing");
            killAll = true;
            if (countAttack > 3) return false;
        }
        return killAll;
    }


    public void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    public void checkMyHp(BufferedImage image) throws Exception {
        checkHP.checkHp(image);
    }

    public BufferedImage takeScreenShot() {
        return capture.takeScreenShot();
    }

    public abstract void mainHandle() throws Exception;
    abstract void runFromMonster() throws Exception;
    abstract void teleport() throws Exception;

    public LocationCheck getLocationCheck() {
        return locationCheck;
    }


}
