package actions.press_on_image.image_params;

import main.Prop;

import java.awt.image.BufferedImage;
import java.util.List;

public class PressCharSelect extends PropsForPress {
    void setValues() {
        sleepTime = 1000;
        pathFragment = Prop.ROOT_DIR + "Interface\\CharSelect\\";
        methodName = "CharSelect";
    }

    public PressCharSelect() {
        super();
        setValues();
    }
}
