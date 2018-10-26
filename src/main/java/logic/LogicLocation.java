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
    private Keys keys;

    public abstract void createThread() throws Exception;

    public void run() {
        try {
            checkHP.initialize(true, Prop.checkHitPoints);
            actions = Actions.instance();
            actions.initialize(loot, usefulLoot);
            checkSP.initialize();
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
            SleepTime.sleep(400);
            if (ATTACK_TIMER.incrementAndGet() > Prop.ATTACK_TIMER) break;
        }
    }

    public void findAndKill(KillMonster monster) {
        try {
            ATTACK_MOBS_BEHIND_WALLS.set(0);
            while (monster.kill()) {
                attackBySwordOrSpell(monster);
                SleepTime.sleep(200);
                count = 0;
            }
            Prop.cast.cast();
//            actions.pickUpCard();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    void attackBySwordOrSpell(KillMonster monster) throws Exception{
        if (Prop.NEED_SPELL_ATTACK && CheckSP.enoughSP) {
            checkMyHp();
            killMonstersAround(monster);
        } else {
            checkMyHp();
            SleepTime.sleep(200);
//            duringTheFight();
            SleepTime.sleep(500);
            killMonstersAround(monster);
            if (ATTACK_MOBS_BEHIND_WALLS.get() > Prop.ATTACK_MOBS_BEHIND_WALLS) {
                actions.teleport();
                LoggerSingle.logInfo("LogicLocation.attackBySwordOrSpell",
                        "teleporting. Mobs behind the walls");
            }
        }
        actions.stepAside(new int[] {100,200});
        actions.pickUpLoot(locationCheck);
        actions.pickUpCard();
//        actions.pickUpLoot(locationCheck);
    }

    void killMonstersAround(KillMonster monster) throws Exception {
        keys = Keys.getInstance();
        while(monster.findAndKillAround()) {
//            duringTheFight();
            checkMyHp();
            SleepTime.sleep(1000);
            LoggerSingle.logInfo("LogicLocation.killMonstersAround",
                    "Find monster around, killing");
        }
//        SleepTime.sleep(500);
//        keys.keyPress(KeyEvent.VK_F8);
    }


    public void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    public void checkMyHp(BufferedImage image) throws Exception {
        checkHP.checkHp(image);
    }


    public abstract void mainHandle() throws Exception;
    abstract void runFromMonster() throws Exception;
    abstract void teleport() throws Exception;





}
