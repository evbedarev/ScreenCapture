package actions.press_on_image.image_params;

import main.Prop;

import java.awt.image.BufferedImage;
import java.util.List;

public class PropsPressClose extends PropsForPress {
    void setValues() {
        pathFragment = Prop.ROOT_DIR + "Interface\\Close\\";
        methodName = "PressClose";
    }

    public PropsPressClose(List<BufferedImage> imageList) {
        setValues();
        this.imageList = imageList;
    }

    public PropsPressClose() {
        super();
        setValues();
    }
}
