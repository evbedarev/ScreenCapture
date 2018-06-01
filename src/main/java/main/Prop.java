package main;

/**
 * Properies
 */
public class Prop {
    private static final int SCREEN_WIDTH = 1600;
    private static final int SCREEN_HEIGHT = 900;
    private static final String ROOT_DIR = "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\";

    private static final int RGB_HP = -6507025;
    private static final int X_HP = 104;
    private static final int Y_HP = 82;
    private static final int X_HP_HEAL = 105;
    private static final int X_HP_TO_RUN = 99;

    private static final int EXCLUDE_X_LEFT = 715;
    private static final int EXCLUDE_X_RIGHT = 740;
    private static final int EXCLUDE_Y_UP = 105;
    private static final int EXCLUDE_Y_DOWN = 130;

    private static final String EMAIL = "madjo1985@gmail.com";
    private static final String EMAIL_PASS = "Ghbdtn85";

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

}
