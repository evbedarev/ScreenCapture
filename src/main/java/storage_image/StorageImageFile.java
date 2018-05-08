package storage_image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StorageImageFile implements StorageImage {
    @Override
    public boolean save(BufferedImage image, String fullFilePath) {
        try {
            File outputfile = new File(fullFilePath);
            ImageIO.write(image,"png",outputfile);
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public BufferedImage load(String fragmentPath) throws IOException{
        BufferedImage fragment;
        File file = new File(fragmentPath);
        fragment = ImageIO.read(file);
        return fragment;
    }
}
