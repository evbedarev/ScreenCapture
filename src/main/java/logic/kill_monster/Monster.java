package logic.kill_monster;

import actions.Actions;
import actions.InterfaceActions;
import actions.SleepTime;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import logic.RgbParameter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Monster implements KillMonster {
    protected List<RgbParameter> rgbParameterList = new ArrayList<>();
    Capture capture;
    public InterfaceActions interfaceActions;
    public Actions actions;
    final Mouse mouse;
    final Keys keys = Keys.getInstance();
    final FindPixels findImageHard;
    private static boolean attackBySpell = false;


    public Monster() throws AWTException {
        capture = Capture.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
        interfaceActions = InterfaceActions.getInstance();
        actions = Actions.instance();
    }

    @Override
    public boolean kill() throws Exception {
        for (int i = 0; i < 3; i++) {
            BufferedImage image = capture.takeScreenShot();

            actions.checkResources(image);
            if (checkDialogWindow(image)) {
                actions.stepAside(new int[]{600, 800});
            }
            if (findAndKill(image)) return true;
        }
        return false;
    }

    @Override
    public boolean findMonster() throws Exception {
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixels3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                LoggerSingle.logInfo(this.toString() + ".findMonster", "Killing monster , coordinates: x=" + x + " y=" + y);
                SleepTime.sleep(1000);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findMonster(BufferedImage image) throws Exception {
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsInImageExcludeArea(
                    image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {780, 825, 348, 449});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                LoggerSingle.logInfo(this.toString() + ".findMonster", "Killing monster , coordinates: x=" + x + " y=" + y);
                SleepTime.sleep(1000);
                return true;
            }
        }
        return false;
    }

    /**
     * Ищет пиксели а экране и жмет при нахождении.
     * @return boolean;
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */

    @Override
    public boolean findAndKill() throws
            Exception {

        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixels3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
//                mouse.mouseClick(x + 5, y + 20);
//                SleepTime.sleep(1500);
                spellAttack();
                mouse.mouseClick(x + 5, y + 20);
                BufferedImage image = capture.takeScreenShot();
                if (checkDialogWindow(image)) {
                    actions.stepAside(new int[] {600, 800});
                }

                LoggerSingle.logInfo(this.toString() + ".findAndKill", "Killing monster , coordinates: x=" + x + " y=" + y);
                sleepAfterAttack();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findAndKill(BufferedImage image) throws Exception {
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsInImage(
                    image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.mouseClick(x + 5, y + 20);
                LoggerSingle.logInfo(this.toString() + ".findMonster", "Killing monster , coordinates: x=" + x + " y=" + y);
                SleepTime.sleep(1000);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean killArround() throws Exception {
        for (int i = 0; i < 3; i++) {
            BufferedImage image = capture.takeScreenShot();

            actions.checkResources(image);

            if (checkDialogWindow(image)) {
                actions.stepAside(new int[]{600, 800});
            }

            if (findAndKillAround(image)) return true;
        }
        return false;
    }

    @Override
    public boolean findAndKillAround() throws
            Exception {
        SleepTime.sleep(500);
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsArround3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {0, 1600, 100, 900});

            if (xy.isPresent()) {
                int rndInt = (int)(Math.random() * 5000);
                int x = xy.get()[0];
                int y = xy.get()[1];
                spellAttack();
                mouse.mouseClick(x + 5, y + 25);
//                if (attackBySpell)
//                    mouse.mouseClick(x + 5, y + 10);
                if (rndInt < 1000) {
                    BufferedImage image = capture.takeScreenShot();
                    if (checkDialogWindow(image)) {
                        actions.stepAside(new int[]{600, 800});
                    }
                }
                LoggerSingle.logInfo(this.toString() + ".findAndKillAround", "Killing monster , coordinates: x=" + x + " y=" + y);
                sleepAfterAttack();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findAndKillAround(BufferedImage image) throws
            Exception {
        SleepTime.sleep(500);
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                    image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {0, 1600, 100, 900});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                spellAttack();
                mouse.mouseClick(x + 5, y + 25);

                LoggerSingle.logInfo(this.toString() + ".findAndKillAround", "Killing monster , coordinates: x=" + x + " y=" + y);
                sleepAfterAttack();
                return true;
            }
        }
        return false;
    }

    private void spellAttack() throws
            InterruptedException {
        int rndInt;
//        if (Prop.NEED_SPELL_ATTACK ) {
//            rndInt = (int)(Math.random() * 2000);
//            System.out.println(rndInt);
//            if (rndInt < 2000) {
//                keys.keyPress(Prop.SPELL_ATTACK_KEY);
//                SleepTime.sleep(200);
//                attackBySpell = true;
//            }
//        }
    }

    private void sleepAfterAttack() throws InterruptedException {
        if (attackBySpell)
            SleepTime.sleep(100);
        else
            SleepTime.sleep(200);
        attackBySpell = false;
    }

    public List<RgbParameter> getRgbParameterList() {
        return rgbParameterList;
    }

    private boolean checkDialogWindow(BufferedImage image) throws Exception {
        boolean wasDialog = false;
        while (interfaceActions.pressNext(image) || interfaceActions.pressClose(image) || interfaceActions.pressOk(image)) {
            image = capture.takeScreenShot();
            mouse.mouseMove(0,0);
            SleepTime.sleep(500);
            LoggerSingle.logInfo(this.toString(), "In dialog");
            wasDialog = true;
        }
        return wasDialog;
    }

}
