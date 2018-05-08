package find_fragments;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface FindFragments {
    public List<BufferedImage> fragments() throws IOException;
}
