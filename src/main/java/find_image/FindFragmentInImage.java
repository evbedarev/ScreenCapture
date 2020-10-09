package find_image;

import find_fragments.FindFragmentFiles;
import logic.screen_shot.Capture;
import logic.screen_shot.ScreenShot;
import main.Prop;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public class FindFragmentInImage {
    private static FindFragmentInImage instance;
    private FindImageHard findImageHard = FindImageHard.getInstance();
    private FindFragmentFiles findFragmentFiles = FindFragmentFiles.getInstance();
    private Capture capture;
    private int[] screen = new int[] {0,1600,0,900};
    private List<BufferedImage> imageList;
    private static BufferedImage screenShot;

    public void setScreen(int[] screen) {
        this.screen = screen;
    }

    private FindFragmentInImage() {
    }

    public static FindFragmentInImage getInstance() {
        if (instance == null) {
            instance = new FindFragmentInImage();
        }
        return instance;
    }

    /**
     * Находи файлы начинающиеся с 'frag' в директории и ищет их на скриншоте.
     * * пример директории: Prop.ROOT_DIR + "Interface\\CheckDie\\"
     * @param dir - директория где находятся файлы
     * @return - координаты найденого изображения.
     * @throws Exception
     */
    public Optional<int[]> findImage(String dir) throws Exception{
        capture = Capture.instance();
        imageList = findFragmentFiles.fragments("frag*", dir);
        Optional<int[]> xy;
        for (BufferedImage image: imageList) {
            screenShot = Prop.context.getBean(ScreenShot.class).pop();
            xy = findImageHard.findImageInArea(screenShot, image,
                    screen);
            if (xy.isPresent())
                return xy;
        }
        return Optional.empty();
    }
    public Optional<int[]> findImage(String dir, int[] scrn) throws Exception{
        capture = Capture.instance();
        imageList = findFragmentFiles.fragments("frag*", dir);
        Optional<int[]> xy;
        for (BufferedImage image: imageList) {
            screenShot = Prop.context.getBean(ScreenShot.class).pop();
            xy = findImageHard.findImageInArea(screenShot, image,
                    scrn);
            if (xy.isPresent())
                return xy;
        }
        return Optional.empty();
    }

    /**
     * Находи файлы начинающиеся с 'frag' в директории и ищет их на скриншоте.
     * пример директории: Prop.ROOT_DIR + "Interface\\CheckDie\\"
     * @param screenshot - скриншот
     * @param dir - директория где находятся файлы
     * @return - координаты найденого изображения.
     * @throws Exception
     */
    public Optional<int[]> findImage(BufferedImage screenshot, String dir) throws Exception{
        imageList = findFragmentFiles.fragments("frag*", dir);
        Optional<int[]> xy;
        for (BufferedImage image: imageList) {
            xy = findImageHard.findImageInArea(screenshot, image,
                    screen);
            if (xy.isPresent())
                return xy;
        }
        return Optional.empty();
    }

    public Optional<int[]> findImage(BufferedImage screenshot, List<BufferedImage> imageList) throws Exception{
        Optional<int[]> xy;
        for (BufferedImage image: imageList) {
            xy = findImageHard.findImageInArea(screenshot, image,
                    screen);
            if (xy.isPresent())
                return xy;
        }
        return Optional.empty();
    }

    public Optional<int[]> findImage(List<BufferedImage> imageList) throws Exception{
        capture = Capture.instance();
        Optional<int[]> xy;
        for (int i = 0; i < 3; i++) {
            for (BufferedImage image: imageList) {
                screenShot = Prop.context.getBean(ScreenShot.class).pop();
                xy = findImageHard.findImageInArea(screenShot, image,
                        screen);
                if (xy.isPresent())
                    return xy;
            }
        }
        return Optional.empty();
    }

    public Optional<int[]> findImageExcludeArea(String dir) throws Exception{
        capture = Capture.instance();
        imageList = findFragmentFiles.fragments("frag*", dir);
        Optional<int[]> xy;
        for (BufferedImage image: imageList) {
            screenShot = Prop.context.getBean(ScreenShot.class).pop();
            xy = findImageHard.findImageExcludeArea(screenShot, image,
                    screen);
            if (xy.isPresent())
                return xy;
        }
        return Optional.empty();
    }

    public BufferedImage getCurrentScreenShot() {
        return screenShot;
    }
}
