package actions.press_on_image.image_params;

import actions.press_on_image.PressOnImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class PropsForPress {
    int[] area = new int[]{0, 1600, 0, 900};
    int sleepTime = 2000;
    String methodName;
    String pathFragment;
    List<BufferedImage> imageList;
    BufferedImage screenShot;

    public PropsForPress(List<BufferedImage> imageList) {
        this.imageList = imageList;
    }

    public PropsForPress() {
    }

    public int[] getArea() {
        return area;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public String getPathFragment() {
        return pathFragment;
    }
    public String getMethodName() {
        return methodName;
    }

    public void setScreenShot(BufferedImage screenShot) {
        this.screenShot = screenShot;
    }

    public List<BufferedImage> getImageList() {
        return imageList;
    }

    public BufferedImage getScreenShot() {
        return screenShot;
    }

    public int typeOfPressParams() {
        if (methodName != null && pathFragment != null && screenShot != null) {
            return 1;
        } else if (methodName != null && screenShot!= null && imageList!= null) {
            return 2;
        } else if (pathFragment != null && methodName != null)
            return 3;
        return 0;
    }
}
