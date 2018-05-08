package storage_image;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface StorageImage {
    boolean save(BufferedImage image, String fullFilePath);
    BufferedImage load(String fragmentPath) throws IOException;
}
