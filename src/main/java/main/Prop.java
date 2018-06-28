package main;

import logic.take_loot.*;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Properies
 */
public class Prop {
    private final Logger logger = Logger.getLogger(this.getClass());

    private static final int SCREEN_WIDTH = 1600;
    private static final int SCREEN_HEIGHT = 900;
    private static final String ROOT_DIR = "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\";

    private static final int RGB_HP = -6507025;
    private static final int X_HP = 104;
    private static final int Y_HP = 82;
    private static final int X_HP_HEAL = 130; //Only for acolyte
    private static final int X_HP_TO_RUN = 110;

    private static final int EXCLUDE_X_LEFT = 715;
    private static final int EXCLUDE_X_RIGHT = 740;
    private static final int EXCLUDE_Y_UP = 105;
    private static final int EXCLUDE_Y_DOWN = 130;

    private static final String EMAIL = "madjo1985@gmail.com";
    private static final String EMAIL_PASS = "";


    private static final int TELEPORT_KEY = KeyEvent.VK_F2;
    private static final int HEAL_KEY = 0;
    private static final int DEFENDER = KeyEvent.VK_F7;
    private static final int GUARD =  KeyEvent.VK_F5;
    private static final int REFLECT =  KeyEvent.VK_F6;
    private static final int AWAKENING_POTION = KeyEvent.VK_F9;

    private TakeLoot[] usefulLoot;
    private TakeLoot[] loot;

    private static final double FIND_LOOT_SMALL_RADIUS = 55;
    private static final double FIND_LOOT_LARGE_RADIUS = 95;

    public TakeLoot[] getUsefulLoot() throws AWTException {
        return new TakeLoot[] {
                new Card(logger),
                new Card1(logger),
                new Clothes(logger),
                new Shield(logger),
                new Mask(logger)
        };
    }

    public static int getAwakeningPotion() {
        return AWAKENING_POTION;
    }

    public static int getDEFENDER() {
        return DEFENDER;
    }

    public static int getGUARD() {
        return GUARD;
    }

    public static int getREFLECT() {
        return REFLECT;
    }


    public static int getTeleportKey() {
        return TELEPORT_KEY;
    }

    public static int getHEAL() {
        return HEAL_KEY;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static String getRootDir() {
        return ROOT_DIR;
    }

    public static int getRgbHp() {
        return RGB_HP;
    }

    public static int getxHp() {
        return X_HP;
    }

    public static int getyHp() {
        return Y_HP;
    }

    public static int getExcludeXLeft() {
        return EXCLUDE_X_LEFT;
    }

    public static int getExcludeXRight() {
        return EXCLUDE_X_RIGHT;
    }

    public static int getExcludeYUp() {
        return EXCLUDE_Y_UP;
    }

    public static int getExcludeYDown() {
        return EXCLUDE_Y_DOWN;
    }

    public static int getxHpHeal() {
        return X_HP_HEAL;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getEmailPass() {
        return EMAIL_PASS;
    }

    public static int getxHpToRun() {
        return X_HP_TO_RUN;
    }

    public static double getFindLootSmallRadius() {
        return FIND_LOOT_SMALL_RADIUS;
    }

    public static double getFindLootLargeRadius() {
        return FIND_LOOT_LARGE_RADIUS;
    }

}
