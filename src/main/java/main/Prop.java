package main;

import actions.InterfaceActions;
import cast.*;
import checks.LocationCheck;
import checks.afterDeath.*;
import checks.check_hp.*;
import checks.location.*;
import logic.*;
import logic.take_loot.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Properies
 */
public class Prop {
    /////////////////GLOBAL////////////////////
    public static Logic logic;
    public static final boolean SUPER_PREMIUM = false;
    public static final boolean CHECK_DIE = true;
    public static final AfterDeath checkDie = new AfterDeathYun04();
    public static Cast cast;
    public static CheckHitPoints checkHitPoints;
    public static InterfaceActions interfaceActions;
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void initialize() throws Exception {
        logic = new LogicCmdField02();
        cast = new CastGunslinger(0);
        checkHitPoints = new CheckHpAndBulletsGuns(context.getBean("locationCheck", LocationCheck.class));
        interfaceActions  = InterfaceActions.getInstance();
        initializeCharacter();
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
    public final static int     ATTACK_MOBS_BEHIND_WALLS = 60;
    //Количество проверок экрана на наличие монстров()
    public final static int COUNT_OF_CHECKS_MONSTER = 4;
    //Через сколько секунд повторить атаку
    public final static int ATTACK_TIMER = 20;
    //Через сколько циклов телепортироваться
    public final static int COUNT_TO_TELEPORT = 1;
    //Сколько надо циклов для поиска лута
    public final static int COUNT_TO_FIND_LOOT = 2;
    ///////////////////////////////////

    public static final int RGB_HP = -6507025;
    public static final int RGB_HP_DEATH = -2695458;
    public static final int X_HP = 130;
    public static final int Y_HP = 82;
    public static final int X_HP_AFTER_DEATH = 42;

    ///////////////HEAL SKILL///////////////////////
    public static final boolean NEED_HEAL = true;
    public static final int HEAL_KEY = KeyEvent.VK_F3;

    public static final int X_HP_HEAL = 110; //Only for acolyte
    public static final int X_HP_TO_RUN = 66;
    public static final int X_HP_TO_END_RUN = 110;
    ///////////////////////////////////////////////

    //////////////////SP///////////////////////////
    public static final boolean NEED_CHECK_SP = false;
    public static final int X_SP = 72;
    public static final int Y_SP = 97;
    public static final int SP_RGB = -6507025;
    public static final int X_SP_ENOUGHT = 158;
    //////////////////////////////////////////////

    ///////////SPELL ATTACK//////////////////////
    public static final boolean NEED_SPELL_ATTACK = false;
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
    public static final int GUARD =  KeyEvent.VK_F5;
    public static final int REFLECT =  KeyEvent.VK_F6;
    public static final int AWAKENING_POTION = KeyEvent.VK_F9;
    public static final int PRESERVE = KeyEvent.VK_F6;


    public TakeLoot[] usefulLoot;
    public TakeLoot[] loot;

    public static final double FIND_LOOT_SMALL_RADIUS = 65;
    public static final double FIND_LOOT_LARGE_RADIUS = 95;

    /////CHARACTER//////
    public static List<RgbParameter> charRgb = new ArrayList<>();

    public static void initializeCharacter() {

        charRgb.add(new RgbParameter(-12967374,
                new int[] {100,110},
                new int[] {-12901838}));
//, -12967631, -6127231,-7840163
//        charRgb.add(new RgbParameter(-7366507,
//                new int[] {4,4},
//                new int[] {-7432041}));
    }


}
