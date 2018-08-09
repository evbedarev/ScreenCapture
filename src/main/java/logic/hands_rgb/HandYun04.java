package logic.hands_rgb;

import logic.RgbParameter;

import java.util.ArrayList;
import java.util.List;

public class HandYun04 implements Hands {
    private List<RgbParameter> rgbParameterList = new ArrayList<>();

    public HandYun04() {
        rgbParameterList.add(new RgbParameter( -1250309,
                new int[] {100,100},
                new int[] {-1250309}));

        rgbParameterList.add(new RgbParameter(-1184259,
                new int[] {100,100},
                new int[] {-1184516}));
//-1973773
//        rgbParameterList.add(new RgbParameter(-1250310,
//                new int[] {100,100},
//                new int[] {-1052678}));

    }

    @Override
    public List<RgbParameter> getRgbParameterList() {
        return rgbParameterList;
    }
}
