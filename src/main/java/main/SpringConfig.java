package main;

import checks.LocationCheck;
import checks.location.*;
import logic.screen_shot.ScreenShot;
import logic.screen_shot.ScreenShots;
import org.springframework.context.annotation.Bean;

import java.awt.*;

public class SpringConfig {
    @Bean
    public ScreenShots screenShotStack() {
        return new ScreenShot();
    }
    @Bean
    public LocationCheck locationCheck() throws AWTException {
        return new LocationCheck(cmdField02());
    }
    @Bean
    public VerifyMap cmdField02() throws AWTException {
        return new CmdField02();
    }
    @Bean
    public VerifyMap aldebaran() throws AWTException {
        return new Aldebaran();
    }
    @Bean
    public VerifyMap beachDun02() throws AWTException {
        return new BeachDun02();
    }
    @Bean
    public VerifyMap beachDun03() throws AWTException {
        return new BeachDun03();
    }
    @Bean
    public VerifyMap cmdField04() throws AWTException {
        return new CmdField04();
    }
    @Bean
    public VerifyMap cmdField07() throws AWTException {
        return new CmdField07();
    }
    @Bean
    public VerifyMap comodo() throws AWTException {
        return new Comodo();
    }
    @Bean
    public VerifyMap einDun01() throws AWTException {
        return new EinDun01();
    }
    @Bean
    public VerifyMap gefField03() throws AWTException {
        return new GefField03();
    }
    @Bean
    public VerifyMap gefField04() throws AWTException {
        return new GefField04();
    }
    @Bean
    public VerifyMap gefField05() throws AWTException {
        return new GefField05();
    }
    @Bean
    public VerifyMap gefField08() throws AWTException {
        return new GefField08();
    }
    @Bean
    public VerifyMap gefField10() throws AWTException {
        return new GefField10();
    }
    @Bean
    public VerifyMap gefField11() throws AWTException {
        return new GefField11();
    }
    @Bean
    public VerifyMap glChurch() throws AWTException {
        return new GlChurch();
    }
    @Bean
    public VerifyMap izludDun03() throws AWTException {
        return new IzludDun03();
    }
    @Bean
    public VerifyMap magmaDun01() throws AWTException {
        return new MagmaDun01();
    }
    @Bean
    public VerifyMap mocField11() throws AWTException {
        return new MocField11();
    }
    @Bean
    public VerifyMap mocField12() throws AWTException {
        return new MocField12();
    }
    @Bean
    public VerifyMap payField07() throws AWTException {
        return new PayField07();
    }
    @Bean
    public VerifyMap prtField07() throws AWTException {
        return new PrtField07();
    }
    @Bean
    public VerifyMap prtField08() throws AWTException {
        return new PrtField08();
    }
    @Bean
    public VerifyMap sphinx03() throws AWTException {
        return new Sphinx03();
    }
    @Bean
    public VerifyMap yunField01() throws AWTException {
        return new YunField01();
    }
    @Bean
    public VerifyMap yunField04() throws AWTException {
        return new YunField04();
    }
    @Bean
    public VerifyMap yunField07() throws AWTException {
        return new YunField07();
    }
    @Bean
    public VerifyMap yunField08() throws AWTException {
        return new YunField08();
    }
    @Bean
    public VerifyMap yunField11() throws AWTException {
        return new YunField11();
    }
    @Bean
    public VerifyMap yunField12() throws AWTException {
        return new YunField12();
    }
}
