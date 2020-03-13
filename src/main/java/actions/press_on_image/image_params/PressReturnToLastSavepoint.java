package actions.press_on_image.image_params;

import main.Prop;

public class PressReturnToLastSavepoint extends PropsForPress {
    void setValues() {
        sleepTime = 2000;
        pathFragment = Prop.ROOT_DIR + "Interface\\CheckDie\\";
        methodName = "PressReturnToLastSavepoint";
    }

    public PressReturnToLastSavepoint() {
        super();
        setValues();
    }
}
