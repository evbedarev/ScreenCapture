package logic.hands_rgb;

import logic.RgbParameter;

import java.util.ArrayList;
import java.util.List;

public class HandYun07 implements Hands {
    private List<RgbParameter> rgbParameterList = new ArrayList<>();

    public HandYun07() {
        rgbParameterList.add(new RgbParameter( -197637,
                new int[] {100,100},
                new int[] {-395013}));
//-1250309
//        rgbParameterList.add(new RgbParameter( -3355677,
//                new int[] {100,100},
//                new int[] {-3355677}));


        rgbParameterList.add(new RgbParameter(-1184516,
                new int[] {100,100},
                new int[] {-987140}));

//        rgbParameterList.add(new RgbParameter(-16382715,
//                new int[] {100,100},
//                new int[] {-3421213}));

    }

    @Override
    public List<RgbParameter> getRgbParameterList() {
        return rgbParameterList;
    }
}
