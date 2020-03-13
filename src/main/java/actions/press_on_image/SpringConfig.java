package actions.press_on_image;

import actions.press_on_image.image_params.PropsForPress;
import actions.press_on_image.image_params.PropsPressOk;
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
    @Bean
    public int[] all_screen() {return new int[]{0, 1600, 0, 900};}

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

    @Bean
    public PropsForPress pressOk() {
        return new PropsPressOk();
    }

    @Bean
    public PropsForPress pressOkWithScreenShot() throws IOException {
        return new PropsPressOk(pressOkList());
    }

//    @Bean
//    public PropsForPress
}
