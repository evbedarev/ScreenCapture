package actions.press_on_image.image_params;

import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class PropsPressOk extends PropsForPress {
    void setValues() {
        sleepTime = 5000;
        pathFragment = Prop.ROOT_DIR + "Interface\\Ok\\";
        methodName = "pressOk";
    }

    public PropsPressOk(List<BufferedImage> imageList) {
        setValues();
        this.imageList = imageList;
    }

    public PropsPressOk() {
        super();
        setValues();
    }
}
