package actions.kafra_actions.put_loot;

import main.Prop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringConfigPutLoot {
    @Bean
    public List<LootToKafra> lootToKafraCmdField02() {
        List<LootToKafra> loot = new ArrayList<>();
        loot.add(new LootToKafra(Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                Prop.ROOT_DIR + "Loot\\BlueHerb\\"));
        loot.add(new LootToKafra(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                Prop.ROOT_DIR + "Loot\\YellowHerb\\"));
        loot.add(new LootToKafra(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                Prop.ROOT_DIR + "Loot\\Panacea\\"));
        loot.add(new LootToKafra(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                Prop.ROOT_DIR + "Loot\\Panacea\\"));
        loot.add(new LootToKafra(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\Cyfar\\"));
        loot.add(new LootToKafra(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\Zargon\\"));
        return loot;
    }
}
