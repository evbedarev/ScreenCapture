package character_cast;

import find_image.FindPixels;
import logic.screen_shot.Capture;
import logic.RgbParameter;
import logic.screen_shot.ScreenShotStack;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class CastCharacterPriest extends CastCharacter {
    private final int threadId;

    public CastCharacterPriest(int threadId) throws AWTException {
        this.threadId = threadId;
    }

    @Override
    public void begin() throws AWTException {
        Thread thread = new CastCharacterPriest(1);
        thread.start();
    }

    @Override
    public void run() {
        try {
            if (threadId ==  1) {
                while (true) {
                    incrementValues();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void cast() throws Exception {
        if (threadId == 0) {
            Optional<int[]> xy = Optional.empty();
            if (ATOMIC_BLESS.get() > 60) {

                keys.keyPress(KeyEvent.VK_F3);
                xy = findCharacter();
                while (!xy.isPresent()) {
                    Thread.sleep(1000);
                    xy = findCharacter();
                }
                mouse.mouseClick(xy.get()[0], xy.get()[1]);
                ATOMIC_BLESS.set(0);
                Thread.sleep(1000);
                keys.keyPress(KeyEvent.VK_F3);
                mouse.mouseClick(800, 410);

            }

            Thread.sleep(1000);
            xy = Optional.empty();
            if (ATOMIC_AGI_UP.get() > 60) {
                keys.keyPress(KeyEvent.VK_F4);
                xy = findCharacter();
                while (!xy.isPresent()) {
                    Thread.sleep(1000);
                    xy = findCharacter();
                }
                mouse.mouseClick(xy.get()[0], xy.get()[1]);
                ATOMIC_AGI_UP.set(0);
                Thread.sleep(1000);
                keys.keyPress(KeyEvent.VK_F4);
                mouse.mouseClick(800, 410);
            }
        }
    }

    @Override
    public void incrementValues() throws InterruptedException {
        incrementConst();
        sleep(1000);
    }


    private Optional<int[]> findCharacter() throws Exception {
        Capture capture = Capture.instance();
        FindPixels findImageHard = new FindPixels();
        Optional<int[]> cachedXY = Optional.empty();


        BufferedImage image = Prop.context.getBean(ScreenShotStack.class).pop();
        for (RgbParameter parameter: Prop.charRgb) {
            Optional<int[]> xy = findImageHard.findPixelsInImageExcludeArea(image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {730, 871, 365, 496});
            if (xy.isPresent()) {
                if ((xy.get()[0] < 771 || xy.get()[0] > 831) && (xy.get()[1] < 469 || xy.get()[1] > 478)) {
                    mouse.mouseClick(xy.get()[0], xy.get()[1]);
                    cachedXY = Optional.of(new int[] {xy.get()[0], xy.get()[1]});
                    System.out.println(xy.get()[0] + ", " + xy.get()[1]);
                }
            }
        }
        return cachedXY;
    }
}
