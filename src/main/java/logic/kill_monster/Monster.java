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
    StringBuilder message = new StringBuilder();
    protected List<RgbParameter> rgbParameterList = new ArrayList<>();
    Capture capture;
    public InterfaceActions interfaceActions;
    public Actions actions;
    static Optional<int[]> xy = Optional.empty();
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
        return findAndKill();
    }

    @Override
    public boolean kill(BufferedImage image) throws Exception {
        return findAndKill(image);
    }

    @Override
    public boolean findMonster() throws Exception {
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        xy = Optional.empty();
        for (RgbParameter parameter: rgbParameterList) {
            xy = findImageHard.findPixels3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                LoggerSingle.logInfo(this.toString() + ".findMonster", createMsg(x,y).toString());
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
        xy = Optional.empty();
        for (RgbParameter parameter: rgbParameterList) {
            xy = findImageHard.findPixelsInImageExcludeArea(
                    image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {780, 825, 348, 449});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                LoggerSingle.logInfo(this.toString() + ".findMonster", createMsg(x,y).toString());
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
        xy = Optional.empty();
        for (RgbParameter parameter: rgbParameterList) {
            xy = findImageHard.findPixels3Times(
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

                LoggerSingle.logInfo(this.toString() + ".findAndKill", createMsg(x,y).toString());
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
        xy = Optional.empty();
        for (RgbParameter parameter: rgbParameterList) {
            xy = findImageHard.findPixelsInImageExcludeArea(
                    image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {0,1600, 0, 900});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];

                mouse.mouseClick(x + 5, y + 20);
                LoggerSingle.logInfo(this.toString() + ".findMonster", createMsg(x,y).toString());
                SleepTime.sleep(500);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findAndKillAround() throws
            Exception {
        SleepTime.sleep(500);
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        xy = Optional.empty();
        for (RgbParameter parameter: rgbParameterList) {
            xy = findImageHard.findPixelsArround3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {650, 950, 275, 600});

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
                LoggerSingle.logInfo(this.toString() + ".findAndKillAround", createMsg(x,y).toString());
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

    private StringBuilder createMsg(int x, int y) {
        message.delete(0,message.length());
        message.append("Killing monster , coordinates: x=");
        message.append(x);
        message.append(" y=");
        message.append(y);
        return message;
    }
}
