package main;

import cast.Cast;
import cast.CastStalker;
import checks.LocationCheck;
import checks.afterDeath.AfterDeath;
import checks.afterDeath.AfterDeathYun04;
import checks.check_hp.CheckHitPoints;
import checks.check_hp.CheckHpStalker;
import checks.location.YunField04;
import logic.Logic;
import logic.LogicWizard.LogicWizardYunField04;
import logic.take_loot.TakeLoot;

import java.awt.event.KeyEvent;

/**
 * Properies
 */
public class PropWizard {
    /////////////////GLOBAL////////////////////
    public static LocationCheck locationCheck;
    public static Logic logic;
    public static final boolean CHECK_DIE = true;
    public static final AfterDeath checkDie = new AfterDeathYun04();
    public static Cast cast;
    public static CheckHitPoints checkHitPoints;


    public static void initialize() throws Exception {
        logic = new LogicWizardYunField04(0);
        cast = new CastStalker(0);
        locationCheck = new LocationCheck(new YunField04());
        checkHitPoints = new CheckHpStalker(locationCheck);
        cast.begin();
    }

    ///////////////////////////////////////////
    public static final int SCREEN_WIDTH = 1600;
    public static final int SCREEN_HEIGHT = 900;
    public static final String ROOT_DIR = "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\";
    public static final String PATH_INVENTORY_MARKER = ROOT_DIR + "Interface\\MarkerInventory\\";
    public static final boolean NEED_AROUND_LOOT_SEARCH = true;

    //SPEED SETTINGS
    //Через сколько секунд улетать если залип курсор атаки на мобе за стеной
    public final static int ATTACK_MOBS_BEHIND_WALLS = 60;
    //Количество проверок экрана на наличие монстров()
    public final static int COUNT_OF_CHECKS_MONSTER = 4;
    //Через сколько секунд повторить атаку
    public final static int ATTACK_TIMER = 20;
    //Через сколько циклов телепортироваться
    public final static int COUNT_TO_TELEPORT = 1;
    //Сколько надо циклов для поиска лута
    public final static int COUNT_TO_FIND_LOOT = 2;
    ///////////////////////////////////

    public static final int RGB_HP = -6507026;
    public static final int RGB_HP_DEATH = -2695458;
    public static final int X_HP = 130;
    public static final int Y_HP = 82;
    public static final int X_HP_AFTER_DEATH = 42;

    ///////////////HEAL SKILL///////////////////////
    public static final boolean NEED_HEAL = true;
    public static final int HEAL_KEY = KeyEvent.VK_F3;
    public static final int X_HP_HEAL = 144; //Only for acolyte
    public static final int X_HP_TO_RUN = 100;
    public static final int X_HP_TO_END_RUN = 144;
    ///////////////////////////////////////////////

    //////////////////SP///////////////////////////
    public static final boolean NEED_CHECK_SP = true;
    public static final int X_SP = 72;
    public static final int Y_SP = 97;
    public static final int SP_RGB = -6507025;
    public static final int X_SP_ENOUGHT = 158;
    //////////////////////////////////////////////

    ///////////SPELL ATTACK//////////////////////
    public static final boolean NEED_SPELL_ATTACK = true;
    public static final int SPELL_ATTACK_KEY = KeyEvent.VK_F8;
    ////////////////////////////////////////////

    public static final int TIMER_CHECK_OVERWEIGHT = 300; //!

    public static final int EXCLUDE_X_LEFT = 715;
    public static final int EXCLUDE_X_RIGHT = 740;

    public static final int EXCLUDE_Y_UP = 105;
    public static final int EXCLUDE_Y_DOWN = 130;

    public static final String EMAIL = "bot270485@gmail.com";
    public static final String EMAIL_PASS = "Qq123456!";


    public static final int TELEPORT_KEY = KeyEvent.VK_F2;
    public static final int WING_KEY = KeyEvent.VK_F1;
    public static final int SHIELD_SPELL = KeyEvent.VK_F7;
    public static final int GUARD = KeyEvent.VK_F5;
    public static final int REFLECT = KeyEvent.VK_F6;
    public static final int AWAKENING_POTION = KeyEvent.VK_F9;
    public static final int PRESERVE = KeyEvent.VK_F6;


    public TakeLoot[] usefulLoot;
    public TakeLoot[] loot;

    public static final double FIND_LOOT_SMALL_RADIUS = 100;
    public static final double FIND_LOOT_LARGE_RADIUS = 95;
}
