package actions.PressOnImage;

import java.awt.image.BufferedImage;
import java.util.List;

public class PropsForPress {
    private int[] area = new int[]{0, 1600, 0, 900};
    private int sleepTime = 2000;
    private String methodName;
    private String pathFragment;
    private List<BufferedImage> imageList;
    public PropsForPress(int[] area,
                         int sleepTime,
                         String methodName,
                         String pathFragment, List<BufferedImage> imageList) {
        this.area = area;
        this.sleepTime = sleepTime;
        this.methodName = methodName;
        this.pathFragment = pathFragment;
        this.imageList = imageList;
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
}
