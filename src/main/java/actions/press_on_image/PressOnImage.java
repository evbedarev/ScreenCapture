package actions.press_on_image;

import actions.SleepTime;
import actions.press_on_image.image_params.PropsForPress;
import find_image.FindFragmentInImage;
import key_and_mouse.Mouse;
import logger.LoggerSingle;

import java.awt.*;
import java.util.Optional;

public class PressOnImage {
    private final Mouse mouse;
    private final FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();

    public PressOnImage() throws AWTException {
        this.mouse = Mouse.getInstance();
    }

    public boolean press(PropsForPress props) throws Exception {
        Optional<int[]> xy = Optional.empty();
        findFragmentInImage.setScreen(props.getArea());
        if (props.typeOfPressParams() == 3)
           xy = findFragmentInImage.findImage(props.getPathFragment());
        else if (props.typeOfPressParams() == 1)
            xy = findFragmentInImage.findImage(props.getScreenShot(),
                                               props.getPathFragment());
        else if (props.typeOfPressParams() == 2)
            xy = findFragmentInImage.findImage(props.getScreenShot(),
                    props.getImageList());
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
            SleepTime.sleep(props.getSleepTime());
            LoggerSingle.logInfo(this.toString() + "+" + props.getMethodName(), ": find and click." );
            return true;
        }
        return false;
    }

}
