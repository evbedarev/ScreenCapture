package find_fragments;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface FindFragments {
    List<BufferedImage> fragments(String wildCard, String rootDir)
            throws IOException;
}
