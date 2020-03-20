package checks;

import actions.SleepTime;
import email.MsgFromPlayer;
import email.SendMessage;
import find_image.FindPixels;
import logic.screen_shot.ScreenShot;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class CheckMsg {
    Logger logger;
    FindPixels findImageHard;
    SendMessage sendMessage = new SendMessage();

    public CheckMsg(Logger logger) throws AWTException {
        this.logger = logger;
        findImageHard = new FindPixels();
    }

    public boolean checkPixelInArea(BufferedImage screenShot, int[] area) throws
            InterruptedException {

        logger.debug("checking location " + this.toString());
        //It's bad, later change. Need to load in constructor.
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -14073229,
                new int[] {2,2},
                new int[] {-14073229},
                new int[] {area[0], area[1], area[2], area[3]});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];

            logger.info("Recived message from player ");
            SleepTime.sleep(200);
            return true;
        }
        return false;
    }

    public void check() throws InterruptedException {
        BufferedImage screenshot = Prop.context.getBean(ScreenShot.class).pop();
        if (checkPixelInArea(screenshot, new int[] {269,279,171,181})) {
            sendMessage.send(new MsgFromPlayer());
            while (true) {
                SleepTime.sleep(5000);
            }
        }
    }


}
