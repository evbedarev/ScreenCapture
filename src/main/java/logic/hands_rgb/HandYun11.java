package logic.hands_rgb;

import logic.RgbParameter;

import java.util.ArrayList;
import java.util.List;

public class HandYun11 implements Hands {
    private List<RgbParameter> rgbParameterList = new ArrayList<>();

    public HandYun11() {
        rgbParameterList.add(new RgbParameter(-1184260,
                new int[] {100,100},
                new int[] {-1184260}));

        rgbParameterList.add(new RgbParameter(-197380,
                new int[] {100,100},
                new int[] {-657924}));

        rgbParameterList.add(new RgbParameter(-394756,
                new int[] {100,100},
                new int[] {-1776394}));
//
//        rgbParameterList.add(new RgbParameter(-1184260,
//                new int[] {100,100},
//                new int[] {-1184260}));
//
//        rgbParameterList.add(new RgbParameter(-1250309,
//                new int[] {100,100},
//                new int[] {-460549}));
//
//        rgbParameterList.add(new RgbParameter(-1250310,
//                new int[] {100,100},
//                new int[] {-1052678}));


    }

    @Override
    public List<RgbParameter> getRgbParameterList() {
        return rgbParameterList;
    }
}
