package actions.press_on_image.image_params;

import main.Prop;

import java.awt.image.BufferedImage;
import java.util.List;

public class PressNextList extends PropsForPress {
    void setValues() {
        sleepTime = 2000;
        pathFragment = Prop.ROOT_DIR + "Interface\\Next\\";
        methodName = "pressNext";
    }

    public PressNextList(List<BufferedImage> imageList) {
        setValues();
        this.imageList = imageList;
    }

    public PressNextList() {
        super();
        setValues();
    }
}
