package main;

import checks.LocationCheck;
import checks.location.*;
import logic.screen_shot.ScreenShot;
import logic.screen_shot.ScreenShots;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class SpringConfig {
    @Bean
    public ScreenShots screenShotStack() {
        return new ScreenShot();
    }
    @Bean
    public LocationCheck locationCheck() throws AWTException {
        return new LocationCheck(gefField08());
    }
    @Bean
    public VerifyMap cmdField02() {
        return new CmdField02();
    }
    @Bean
    public VerifyMap aldebaran() {
        return new Aldebaran();
    }
    @Bean
    public VerifyMap beachDun02() {
        return new BeachDun02();
    }
    @Bean
    public VerifyMap beachDun03() {
        return new BeachDun03();
    }
    @Bean
    public VerifyMap cmdField04() {
        return new CmdField04();
    }
    @Bean
    public VerifyMap cmdField07() {
        return new CmdField07();
    }
    @Bean
    public VerifyMap comodo() {
        return new Comodo();
    }
    @Bean
    public VerifyMap einDun01() {
        return new EinDun01();
    }
    @Bean
    public VerifyMap gefField03() {
        return new GefField03();
    }
    @Bean
    public VerifyMap gefField04() {
        return new GefField04();
    }
    @Bean
    public VerifyMap gefField05() {
        return new GefField05();
    }
    @Bean
    public VerifyMap gefField08() {
        return new GefField08();
    }
    @Bean
    public VerifyMap gefField10() {
        return new GefField10();
    }
    @Bean
    public VerifyMap gefField11() {
        return new GefField11();
    }
    @Bean
    public VerifyMap glChurch() {
        return new GlChurch();
    }
    @Bean
    public VerifyMap izludDun03() {
        return new IzludDun03();
    }
    @Bean
    public VerifyMap magmaDun01() {
        return new MagmaDun01();
    }
    @Bean
    public VerifyMap mocField11() {
        return new MocField11();
    }
    @Bean
    public VerifyMap mocField12() {
        return new MocField12();
    }
    @Bean
    public VerifyMap payField07() {
        return new PayField07();
    }
    @Bean
    public VerifyMap prtField07() {
        return new PrtField07();
    }
    @Bean
    public VerifyMap prtField08() {
        return new PrtField08();
    }
    @Bean
    public VerifyMap sphinx03() {
        return new Sphinx03();
    }
    @Bean
    public VerifyMap yunField01() {
        return new YunField01();
    }
    @Bean
    public VerifyMap yunField04() {
        return new YunField04();
    }
    @Bean
    public VerifyMap yunField07() {
        return new YunField07();
    }
    @Bean
    public VerifyMap yunField08() {
        return new YunField08();
    }
    @Bean
    public VerifyMap yunField11() {
        return new YunField11();
    }
    @Bean
    public VerifyMap yunField12() {
        return new YunField12();
    }
}
