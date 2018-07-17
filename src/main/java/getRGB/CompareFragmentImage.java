package getRGB;

import main.Prop;
import org.apache.log4j.Logger;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс сравнения RGB.
 */
public class CompareFragmentImage {

    private static final String ROOT_PATH = Prop.ROOT_DIR;
    private static final String PATH_FRAGMENT = ROOT_PATH + "getRGB\\frag.png";
    public static final  Logger logger = Logger.getLogger(CompareFragmentImage.class.getName());

    /**
     * Метод сравнивает 5 изображение между собой и выводит одинаковые пиксели
     * присутсвующие во всех изображениях на экран.
     * @throws Exception
     */
    public void getRgb() throws Exception {
        HashMap<Integer, List<RgbClass>> equalsOne;
        HashMap<Integer, List<RgbClass>> equalsTwo;
        equalsOne = compareImages(ROOT_PATH + "getRGB\\frag1.png", ROOT_PATH + "getRGB\\frag2.png");
        equalsTwo = compareImages(ROOT_PATH + "getRGB\\frag3.png", ROOT_PATH + "getRGB\\frag4.png");

        logger.info("Equals rgb for fragments 0, 1 ,2 is:");
        for (Map.Entry<Integer, List<RgbClass>> entry: equalsOne.entrySet()) {
            for (RgbClass rgbClass: entry.getValue()) {
                logger.info("equals RGB = " + entry.getKey() + " x=" + rgbClass.getX() + " y=" + rgbClass.getY());
            }

        }


        logger.info("Equals rgb for fragments 0, 3 ,4 is:");
        for (Map.Entry<Integer, List<RgbClass>> entry: equalsTwo.entrySet()) {
            for (RgbClass rgbClass: entry.getValue()) {
                logger.info("equals RGB = " + entry.getKey() + " x=" + rgbClass.getX()
                        + " y=" + rgbClass.getY());
            }
        }

        for (Map.Entry<Integer, List<RgbClass>> entryOne: equalsOne.entrySet()) {
            for (Map.Entry<Integer, List<RgbClass>> entryTwo: equalsTwo.entrySet()) {
                if (entryOne.getKey().equals(entryTwo.getKey())) {
                    for (RgbClass rgbClass: entryOne.getValue()) {
                        logger.info("All images equals RGB = " + entryOne.getKey() + " x=" + rgbClass.getX()
                                + " y=" + rgbClass.getY());
                    }
                }
            }
        }
    }

    /**
     * Принимает 3 изображения, находит между ними схожие пиксели.
     * @param pathFragment1 - фрагмент 1го изображения
     * @param pathFragment2 - фрагмент 2го изображения
     * @return - Возвращает список одинаковых цветов ргб.
     * @throws Exception
     */
    private HashMap<Integer, List<RgbClass>> compareImages(String pathFragment1, String pathFragment2) throws Exception {
        HashMap<Integer, List<RgbClass>> firstMapRgb;
        HashMap<Integer, List<RgbClass>> secMapRgb;
        HashMap<Integer, List<RgbClass>> resultMapRgb = new HashMap<>();


        logger.info("Finding common pixels in images: " + PATH_FRAGMENT + " & " + pathFragment1);
        firstMapRgb = findRGB(PATH_FRAGMENT, pathFragment1);
        logger.info("Finding common pixels in images: " + PATH_FRAGMENT + " & " + pathFragment2);
        secMapRgb = findRGB(PATH_FRAGMENT, pathFragment2);

        for (Map.Entry<Integer, List<RgbClass>> entryOne: firstMapRgb.entrySet()) {
            for (Map.Entry<Integer, List<RgbClass>> entryTwo: secMapRgb.entrySet()) {
                if (entryOne.getKey().equals(entryTwo.getKey())) {
                    resultMapRgb.put(entryOne.getKey(), entryOne.getValue());
                }
            }
        }
        return resultMapRgb;
    }

    /**
     * Находит похожие пиксели между двумя изображениями
     * @param path1 - изображение 1
     * @param path2 - изображение 2
     * @return - Список схожих ргб.
     * @throws Exception
     */
    public static HashMap<Integer, List<RgbClass>> findRGB(String path1, String path2) throws Exception {
        HashMap<Integer, List<RgbClass>> mapRgb = new HashMap<>();
        StorageImage storageImage = StorageImageFile.instance();
        List<RgbClass> rgbClassList;
        List<Integer> rgbList = new ArrayList<>();

        BufferedImage image = storageImage.load(path1);
        BufferedImage fragment = storageImage.load(path2);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x=0; x < image.getWidth(); x++) {

                for (int yy = 0; yy < fragment.getHeight(); yy++) {
                    for (int xx = 0; xx < fragment.getWidth(); xx++) {

                        if (image.getRGB(x, y) == fragment.getRGB(xx , yy)) {
                            rgbClassList = new ArrayList<>();
                            rgbClassList.add(new RgbClass(x, y));
                            mapRgb.put(image.getRGB(x, y), rgbClassList);
                        }
                    }
                }
            }
        }

        for (Map.Entry<Integer, List<RgbClass>> entry: mapRgb.entrySet()) {
            logger.info(entry.getKey() + " RGB  ");
            rgbList.add(entry.getKey());
            for (RgbClass rgbClass: entry.getValue()) {
                logger.info("x: " + rgbClass.getX() + " y: " + rgbClass.getY());
            }
        }
        return mapRgb;
    }
}
