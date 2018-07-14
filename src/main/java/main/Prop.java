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

    //SPEED SETTINGS
    //Через сколько секунд улетать если залип курсор атаки на мобе за стеной
    public final static int ATTACK_MOBS_BEHIND_WALLS = 60;
    //Количество проверок экрана на наличие монстров()
    public final static int COUNT_OF_CHECKS_MONSTER = 5;
    //Через сколько секунд повторить атаку
    public final static int ATTACK_TIMER = 10;
    //Через сколько циклов телепортироваться
    public final static int COUNT_TO_TELEPORT = 3;
    //Сколько надо циклов для поиска лута
    public final static int COUNT_TO_FIND_LOOT = 5;
    ///////////////////////////////////


    public final Logger logger = Logger.getLogger(this.getClass());

    public static final int SCREEN_WIDTH = 1600;
    public static final int SCREEN_HEIGHT = 900;
    public static final String ROOT_DIR = "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\";

    public static final boolean NEED_AROUND_LOOT_SEARCH = true;

    public static final int RGB_HP = -6507025;
    public static final int X_HP = 130;
    public static final int Y_HP = 82;

    ///////////////HEAL SKILL///////////////////////
    public static final boolean NEED_HEAL = false;
    public static final int HEAL_KEY = KeyEvent.VK_F1;
    public static final int X_HP_HEAL = 137; //Only for acolyte
    public static final int X_HP_TO_RUN = 110;
    ///////////////////////////////////////////////

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
