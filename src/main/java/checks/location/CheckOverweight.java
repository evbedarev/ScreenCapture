package checks.location;

import find_image.FindFragmentInImage;
import main.Prop;

public class CheckOverweight {
    static FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();
    public static boolean check() throws Exception {
        findFragmentInImage.setScreen(new int[]{40, 150, 140, 160});
        return findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\Overweight\\").isPresent();
    }
}
