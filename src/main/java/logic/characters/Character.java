package logic.characters;

import actions.SleepTime;
import find_image.FindFragmentInImage;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.RgbParameter;
import logic.screen_shot.ScreenShot;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

public class Character implements Characters {
    final Mouse mouse;
    final Keys keys = Keys.getInstance();
    final FindPixels findImageHard;
    final FindFragmentInImage findFragmentInImage;
    Optional<int[]> cachedXY = Optional.empty();
    private static boolean attackBySpell = false;


    public Character() throws AWTException {
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
        findFragmentInImage = FindFragmentInImage.getInstance();
    }

    @Override
    public boolean findCharacter() throws Exception {

        LoggerSingle.logDebug(this.toString(), "Finding character ");

        BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
        for (RgbParameter parameter: Prop.charRgb) {
            Optional<int[]> xy = findImageHard.findPixelsInImageExcludeArea(image,
                            parameter.getMainRgb(),
                            parameter.getSubImageSize(),
                            parameter.getAncillaryRgb(),
                            new int[] {730, 871, 365, 496});
            if (xy.isPresent()) {
//                keys.keyPress(KeyEvent.VK_F3);
//                Thread.sleep(1000);
                if ((xy.get()[0] < 771 || xy.get()[0] > 831) && (xy.get()[1] < 469 || xy.get()[1] > 478)) {
                    mouse.mouseClick(xy.get()[0], xy.get()[1] - 50);
                    cachedXY = Optional.of(new int[] {xy.get()[0], xy.get()[1]});
                    System.out.println(xy.get()[0] + ", " + xy.get()[1]);
                    return true;
                }
            }
//            else {
//                if (cachedXY.isPresent()) {
//                    int[] xyCoord;
//                    xyCoord = moveMouseDirectly(cachedXY.get()[0],cachedXY.get()[1]);
//                    mouse.mouseClick(xyCoord[0], xyCoord[1]);
//                    cachedXY = Optional.empty();
//                }
//            }
        }
        return false;
    }

    private int[] moveMouseDirectly(int x2, int y2) {
        double x, y, x1, y1;
        x1 = 800;
        y1 = 450;
        if (x1 > x2)
            x = x2 - 200;
        else
            x = x2 + 200;

        y = ((x - x1)/(x2 - x1))*(y2 - y1) + y1;
        System.out.println(x + "," + y);
        return new int[] {(int)x, (int)y};
    }

    /**
     * Ищет пиксели а экране и жмет при нахождении.
     * @return boolean;
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */

    @Override
    public boolean follow() throws
            Exception {

        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: Prop.charRgb) {
            Optional<int[]> xy = findImageHard.findPixels3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
//                spellAttack();.
                mouse.mouseClick(x + 5, y + 50);
                LoggerSingle.logInfo(this.toString() + ".findAndKill", "Killing monster , coordinates: x=" + x + " y=" + y);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findAround() throws
            AWTException,
            InterruptedException {
        SleepTime.sleep(500);
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: Prop.charRgb) {
            Optional<int[]> xy = findImageHard.findPixelsArround3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {400, 1100, 75, 700});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                mouse.mouseClick(x + 5, y + 25);
//                if (attackBySpell)
//                    mouse.mouseClick(x + 5, y + 10);

                LoggerSingle.logInfo(this.toString() + ".findAndKillAround", "Killing monster , coordinates: x=" + x + " y=" + y);
                return true;
            }
        }
        return false;
    }

}
