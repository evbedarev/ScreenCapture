package actions.PressOnImage;

import find_fragments.FindFragmentFiles;
import main.Prop;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOfProps {
    public static PropsForPress getMapOfProps(String key) {
        return mapOfProps.get(key);
    }

    private final static Map<String, PropsForPress> mapOfProps = new HashMap<>();
    private static final FindFragmentFiles findFragmentFiles = FindFragmentFiles.getInstance();
    private static final int[] ALL_SCREEN = new int[]{0, 1600, 0, 900};
    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    public MapOfProps() {
       mapOfProps.put("pressNext", new PropsForPress(ALL_SCREEN,
               2000, "pressNext", null, null));
       mapOfProps.put("pressNextWithImage", new PropsForPress(ALL_SCREEN,
               2000,
               "pressNext",
               null,
               (List<BufferedImage>) context.getBean("pressNextList",  List.class)));
    }
}
