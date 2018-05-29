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

    private static final String ROOT_PATH = Prop.getRootDir();
    private static final String PATH_FRAGMENT = ROOT_PATH + "getRGB\\frag.png";
    public static final  Logger logger = Logger.getLogger(CompareFragmentImage.class.getName());

    /**
     * Метод сравнивает 5 изображение между собой и выводит одинаковые пиксели
     * присутсвующие во всех изображениях на экран.
     * @throws Exception
     */
    public void getRgb() throws Exception {
        List<Integer> equalsOne;
        List<Integer> equalsTwo;
        equalsOne = copareImages(ROOT_PATH + "getRGB\\frag1.png", ROOT_PATH + "getRGB\\frag2.png");
        equalsTwo = copareImages(ROOT_PATH + "getRGB\\frag3.png", ROOT_PATH + "getRGB\\frag4.png");

        logger.info("Equals rgb for fragments 0, 1 ,2 is:");
        for (Integer i: equalsOne) {
            logger.info("equals RGB = " + i);
        }


        logger.info("Equals rgb for fragments 0, 3 ,4 is:");
        for (Integer i: equalsTwo) {
            logger.info("equals RGB = " + i);
        }


        for (Integer i: equalsOne) {
            for (Integer j: equalsTwo) {
                if (i.equals(j)) {
                    logger.info("All images equals RGB = " + i);
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
    private  List<Integer> copareImages(String pathFragment1, String pathFragment2) throws Exception {
        List<Integer> firsRGB;
        List<Integer> secondRGB;
        List<Integer> equalsOne = new ArrayList<>();

        logger.info("Finding common pixels in images: " + PATH_FRAGMENT + " & " + pathFragment1);
        firsRGB = findRGB(PATH_FRAGMENT, pathFragment1);
        logger.info("Finding common pixels in images: " + PATH_FRAGMENT + " & " + pathFragment2);
        secondRGB = findRGB(PATH_FRAGMENT, pathFragment2);

        for (Integer i: firsRGB) {
            for (Integer j: secondRGB) {
                if (i.equals(j)) {
                    equalsOne.add(i);
                }
            }
        }
        return equalsOne;
    }

    /**
     * Находит похожие пиксели между двумя изображениями
     * @param path1 - изображение 1
     * @param path2 - изображение 2
     * @return - Список схожих ргб.
     * @throws Exception
     */
    public static List<Integer> findRGB(String path1, String path2) throws Exception {
        HashMap<Integer, List<RgbClass>> mapRgb = new HashMap<>();
        StorageImage storageImage = new StorageImageFile();
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
        return rgbList;
    }
}
