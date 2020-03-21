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
    @Bean
    public TakeLoot[] lootGefField08() throws AWTException {
        return new TakeLoot[] {new WhiteHerb(), new DragonTail(), new Zargon()};
    }
    @Bean TakeLoot[] usefulGefField08() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon(), new Elunium()};
    }
    @Bean
    public TakeLoot[] lootGefField10() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulGefField10() throws AWTException {
        // new Clothes(), new Shield(), new Mask()
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootGefField11() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulGefField11() throws AWTException {
        // new Clothes(), new Shield(), new Mask()
        return new TakeLoot[] {new Card(), new Coupon(), new Shield()};
    }
    @Bean
    public TakeLoot[] lootGlChurch() throws AWTException {
        return new TakeLoot[] {new Fabric(), new YggdrasilLeaf(), new WhiteHerb()};
    }
    @Bean TakeLoot[] usefulGlChurch() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootHerbLocation01() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulHerbLocation01() throws AWTException {
        return new TakeLoot[] {new BlueHerb()};
    }
    @Bean
    public TakeLoot[] lootInSphinx3() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulInSphinx3() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootMocField01() throws AWTException {
        return new TakeLoot[] {new Bottle()};
    }
    @Bean TakeLoot[] usefulMocField01() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootMocField11() throws AWTException {
        return new TakeLoot[] {new Bottle()};
    }
    @Bean TakeLoot[] usefulMocField11() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon(), new Clothes()};
    }
    @Bean
    public TakeLoot[] lootMocField12() throws AWTException {
        return lootMocField11();
    }
    @Bean TakeLoot[] usefulMocField12() throws AWTException {
        return usefulMocField11();
    }
    @Bean
    public TakeLoot[] lootPayField07() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulPayField07() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootPrtField07() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulPrtField07() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootYunField04() throws AWTException {
        return new TakeLoot[] {new HarpyFeather(), new HarpyTalon()};
    }
    @Bean TakeLoot[] usefulYunField04() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootYunField07() throws AWTException {
//                new AntelopeHorn(),
        return new TakeLoot[] {new Bottle(), new AntelopeSkin(),new HarpyTalon(), new HarpyFeather()};
    }
    @Bean TakeLoot[] usefulYunField07() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }
    @Bean
    public TakeLoot[] lootYunField08() throws AWTException {
        return new TakeLoot[] {new WindOfVerdure(), new Bottle(), new PecoFeather()};
    }
    @Bean TakeLoot[] usefulYunField08() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon(), new BlueHerb(), new MastellaFruit()};
    }
    @Bean
    public TakeLoot[] lootYunField11() throws AWTException {
        return new TakeLoot[] {new AntelopeSkin(), new Bottle()};
    }
    @Bean TakeLoot[] usefulYunField11() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon(), new BlueHerb()};
    }
    @Bean
    public TakeLoot[] lootYunField12() throws AWTException {
        return new TakeLoot[] {};
    }
    @Bean TakeLoot[] usefulYunField12() throws AWTException {
        return new TakeLoot[] {new Card(), new Coupon()};
    }

}
