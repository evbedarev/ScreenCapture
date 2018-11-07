package logic.hands_rgb;

import logic.RgbParameter;

import java.util.ArrayList;
import java.util.List;

public class HandSph03 implements Hands {
    private List<RgbParameter> rgbParameterList = new ArrayList<>();

    public HandSph03() {
        rgbParameterList.add(new RgbParameter(-1184516,
                new int[] {100,100},
                new int[] {-1118467}));

        rgbParameterList.add(new RgbParameter(-1315846,
                new int[] {100,100},
                new int[] {-526341, -1315845}));
    }

    @Override
    public List<RgbParameter> getRgbParameterList() {
        return rgbParameterList;
    }
}
