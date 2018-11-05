package logic;

import actions.Actions;
import actions.SleepTime;
import checks.*;
import checks.afterDeath.AfterDeath;
import checks.check_hp.CheckHP;
import key_and_mouse.Keys;
import logger.LoggerSingle;
import logic.attacks.Attack;
import logic.kill_monster.*;
import logic.move_by_card.MoveByCard;
import logic.take_loot.LootAround;
import logic.take_loot.TakeLoot;
import main.Prop;

import java.awt.image.BufferedImage;
import java.util.List;

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
    static Actions actions;
    static LocationCheck locationCheck;
    static LootAround lootAround = LootAround.getInstance();
    static MoveByCard moveByCard;
    private Keys keys;
    Capture capture;
    private int cntAttack;
    Human human;

    public abstract void createThread() throws Exception;

    public void run() {
        try {
            checkHP.initialize(true, Prop.checkHitPoints);
            actions = Actions.instance();
            actions.initialize(loot, usefulLoot);
            checkSP.initialize();
            capture = Capture.instance();
            human = new Human();
            while (true) {
                mainHandle();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void findAndKill(KillMonster monster) {
        try {
            for (int i = 0; i < 3; i++) {
                findAndKill(monster, capture.takeScreenShot());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void findAndKill(KillMonster monster, BufferedImage image) {
        try {
            cntAttack = 0;
            while (monster.kill(image)) {

                if (human.findMonster(image)) {
                    actions.useWing();
                    break;
                }

                attackBySwordOrSpell(monster);
                SleepTime.sleep(400);

                if (cntAttack > 4) {
                    actions.useWing();
                    SleepTime.sleep(2000);
                    cntAttack = 0;
                    LoggerSingle.logInfo("LogicLocation.findAndKill",
                            "use wing. can't walk to the monster");
                    break;
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
            SleepTime.sleep(1000);
            if (!killMonstersAround(monster)) {
                cntAttack++;
            } else {
                cntAttack = 0;
            }
        }
        actions.stepAside(new int[] {100,200});
        actions.pickUpCard();
//        actions.pickUpLoot(locationCheck);
    }

    boolean killMonstersAround(KillMonster monster) throws Exception {
        keys = Keys.getInstance();
        boolean killAll = false;
        while(monster.findAndKillAround()) {
            checkMyHp();
            SleepTime.sleep(900);
            LoggerSingle.logInfo("LogicLocation.killMonstersAround",
                    "Find monster around, killing");
            killAll = true;
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
