package logic.take_loot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class SpringConfigLoot {
    @Bean
    public TakeLoot[] lootBeachDun02() throws AWTException {
        return new TakeLoot[] {new Brigan(), new MudLamp()};
    }
    @Bean TakeLoot[] usefulLootBeachDun02() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon(), new Elunium()};
    }
    @Bean
    public TakeLoot[] lootBeachDun03() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulLootBeachDun03() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootCmdField02() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulLootCmdField02() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon(), new BlueHerb(), new Cyfar(), new Zargon()};
    }
    @Bean
    public TakeLoot[] lootCmdField04() throws AWTException {
        return new TakeLoot[] {new Cyfar(), new SeaOtterFur(), new GlassBead()};
    }
    @Bean TakeLoot[] usefulLootCmdField04() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootCmdField07() throws AWTException {
        return new TakeLoot[] {new Cyfar(), new WindOfVerdure()};
    }
    @Bean TakeLoot[] usefulLootCmdField07() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon(), new Shield(), /*new Clothes(), new Mask()*/};
    }
    @Bean
    public TakeLoot[] lootEinDun01() throws AWTException {
        return new TakeLoot[] {new GunPowder(), new Steel()};
    }
    @Bean TakeLoot[] usefulLootEinDun01() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon(), new Shield()};
    }
    @Bean
    public TakeLoot[] lootGefField03() throws AWTException {
        return new TakeLoot[] {new AntelopeSkin(), new BlueHerb(), new Bottle()};
    }
    @Bean TakeLoot[] usefulGefField03() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon(), new BlueHerb()};
    }
    @Bean
    public TakeLoot[] lootGefField05() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulGefField05() throws AWTException {
        // new Clothes(), new Shield(), new Mask()
        return new TakeLoot[] {new Card(), new Coupon()};
    }

}
