package actions.PressOnImage;

import find_fragments.FindFragmentFiles;
import main.Prop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Configuration
public class SpringConfig {
    private static final int[] ALL_SCREEN = new int[]{0, 1600, 0, 900};
    @Bean
    public PressOnImage pressOnImage() throws AWTException {
        return new PressOnImage();
    }

    @Bean
    public List<BufferedImage> pressOkList() throws IOException {
        return FindFragmentFiles.getInstance().fragments("frag", Prop.ROOT_DIR + "Interface\\Ok\\");
    }

    @Bean
    public List<BufferedImage> pressNextList() throws IOException {
        return FindFragmentFiles.getInstance().fragments("frag", Prop.ROOT_DIR + "Interface\\Next\\");
    }

    @Bean
    public List<BufferedImage> pressCloseList() throws IOException {
        return FindFragmentFiles.getInstance().fragments("frag", Prop.ROOT_DIR + "Interface\\Close\\");
    }
}
