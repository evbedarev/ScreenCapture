package main;

import logic.take_loot.*;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Properies
 */
public class Prop {
//    public final Logger logger = Logger.getLogger(this.getClass());
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
    public final static int ATTACK_TIMER = 10;
    //Через сколько циклов телепортироваться
    public final static int COUNT_TO_TELEPORT = 1;
    //Сколько надо циклов для поиска лута
    public final static int COUNT_TO_FIND_LOOT = 2;
    ///////////////////////////////////

    public static final int RGB_HP = -6507025;
    public static final int X_HP = 130;
    public static final int Y_HP = 82;

    ///////////////HEAL SKILL///////////////////////
    public static final boolean NEED_HEAL = false;
    public static final int HEAL_KEY = KeyEvent.VK_F1;
    public static final int X_HP_HEAL = 137; //Only for acolyte
    public static final int X_HP_TO_RUN = 110;
    public static final int X_HP_TO_END_RUN = 150;
    public static final int X_HP_TO_DIE = 39;
    ///////////////////////////////////////////////

    //////////////////SP///////////////////////////
    public static final int X_SP = 103;
    public static final int Y_SP = 97;
    public static final int SP_RGB = -6507025;
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

    public static final String EMAIL = "madjo1985@gmail.com";
    public static final String EMAIL_PASS = "Ghbdtn85";


    public static final int TELEPORT_KEY = KeyEvent.VK_F2;
    public static final int WING_KEY = KeyEvent.VK_F3;
    public static final int DEFENDER = KeyEvent.VK_F7;
    public static final int GUARD =  KeyEvent.VK_F5;
    public static final int REFLECT =  KeyEvent.VK_F6;
    public static final int AWAKENING_POTION = KeyEvent.VK_F9;

    public TakeLoot[] usefulLoot;
    public TakeLoot[] loot;

    public static final double FIND_LOOT_SMALL_RADIUS = 55;
    public static final double FIND_LOOT_LARGE_RADIUS = 95;
}
