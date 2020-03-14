package actions.press_on_image;

import actions.press_on_image.image_params.*;
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

    @Bean
    public PropsForPress pressNext() {
        return new PressNextList();
    }

    @Bean
    public PropsForPress pressNextWithScreenShot() throws IOException {
        return new PressNextList(pressNextList());
    }

    @Bean
    public PropsForPress pressReturnToLastSavepoint() {
        return new PressReturnToLastSavepoint();
    }
    @Bean
    public PropsForPress pressForOpenWarehouse() {
        return new PropsForPress("PressForOpenWarehouse", Prop.ROOT_DIR + "Interface\\OpenWarehouse\\");
    }

    @Bean
    public PropsForPress pressClose() {
        return new PropsPressClose();
    }

    @Bean
    public PropsForPress pressCloseWithScreenShot() throws IOException {
        return new PropsPressClose(pressCloseList());
    }
    @Bean
    public PropsForPress pressOnKafraTeleportButton() {
        return new PropsForPress("PressOnKafraTeleportButton ",Prop.ROOT_DIR + "Interface\\KafraTeleport\\");
    }
    @Bean
    public PropsForPress pressOnDownArrow() {
        return new PropsForPress("PressOnDownArrow",Prop.ROOT_DIR + "Interface\\DownArrow\\");
    }
    @Bean
    public PropsForPress pressCmdField07() {
        return new PropsForPress("TeleportCmdField07",Prop.ROOT_DIR + "Interface\\TeleportCmdField07\\");
    }
    @Bean
    public PropsForPress pressGefField10() {
        return new PropsForPress("TeleportGefField10",Prop.ROOT_DIR + "Interface\\TeleportGefField10\\");
    }
    @Bean
    public PropsForPress pressCharSelect() {
        return new PressCharSelect();
    }
}
