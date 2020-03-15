package actions.press_on_image.actions_spring;

import actions.SleepTime;
import checks.LocationCheck;
import key_and_mouse.Mouse;
import logger.LoggerSingle;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StepAside {
    private Mouse mouse = Mouse.getInstance();
    private List<int[]> stepAsideList = new ArrayList<>();
    private int i = 0;
    private int[] radius = new int[]{600, 800};

    public StepAside() throws AWTException {
    }

    @PostConstruct
    void initialize() throws Exception {
        for (int i = 0; i < 100; i++) {
            stepAsideList.add(stepAside(radius));
        }
    }

    public void stepAsideSimple() throws Exception {
        if (i > 99)
            i = 0;
        mouse.mouseClick(stepAsideList.get(i)[0],stepAsideList.get(i)[1]);
        SleepTime.sleep(300);
        LoggerSingle.logInfo(this.toString(), "Step aside without Location check");
        i++;
    }

    public Optional<int[]> stepAside(LocationCheck locationCheck,
                                     int[] radiuses, boolean needCoords) throws Exception {
        if (i > 99)
            i = 0;
        Optional<int[]> xy;
        locationCheck.locationCheck();
        xy = Optional.of(stepAside(stepAsideList.get(i)));
        mouse.mouseClick(xy.get()[0], xy.get()[1]);
        SleepTime.sleep(300);
        locationCheck.locationCheck();
        i++;
        return xy;
    }

    public int[] stepAside(int[] radiuses) throws Exception {
        double t = 2 * Math.PI * Math.random();
        double minRadius = radiuses[0];
        double maxRadius = radiuses[1];
        double x = minRadius * Math.cos(t);
        double x1 = maxRadius * Math.cos(t);
        double mediumX = x + Math.random()*(x1 - x);
        double mediumR = mediumX/Math.cos(t);
        double mediumY = mediumR * Math.sin(t);
        return new int[] {800 + (int) Math.round(mediumX),450 + (int) Math.round(mediumY)};
    }
}
