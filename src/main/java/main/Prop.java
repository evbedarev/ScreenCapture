package main;

/**
 * Properies
 * SIGELTON
 */
public class Prop {
    private static final int SCREEN_WIDTH = 1600;
    private static final int SCREEN_HEIGHT = 900;
    private static final String ROOT_DIR = "C:\\java\\ScreenCapture\\src\\main\\resources\\";

    private static final int RGB_HP = -6507025;
    private static final int X_HP = 98;
    private static final int Y_HP = 82;

    private static final int EXCLUDE_X_LEFT = 715;

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getEmailPass() {
        return EMAIL_PASS;
    }

    private static final int EXCLUDE_X_RIGHT = 740;
    private static final int EXCLUDE_Y_UP = 105;
    private static final int EXCLUDE_Y_DOWN = 130;

    private static final int RGB_HP_HEAL = -6507025;
    private static final int X_HP_HEAL = 145;
    private static final int Y_HP_HEAL = 82;

    private static final String EMAIL = "";
    private static final String EMAIL_PASS = "";

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

    public static int getRgbHpHeal() {
        return RGB_HP_HEAL;
    }

    public static int getxHpHeal() {
        return X_HP_HEAL;
    }

    public static int getyHpHeal() {
        return Y_HP_HEAL;
    }
}