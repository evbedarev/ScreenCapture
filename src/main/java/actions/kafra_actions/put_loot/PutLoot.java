package actions.kafra_actions.put_loot;

import actions.InterfaceActions;
import actions.SleepTime;
import main.Prop;
import java.util.List;

public class PutLoot implements  KafraActionsPutLoot{
    public List<LootToKafra> kafraLootList = Prop.lootToKafra;

    @Override
    public void putLootToKafra() throws Exception {
        InterfaceActions interfaceActions = InterfaceActions.getInstance();
        interfaceActions.pressOnKafra();
        interfaceActions.pressNext();
        interfaceActions.openWarehouse();
        interfaceActions.pressOk();
        interfaceActions.pressClose();
        interfaceActions.openInventory();
        for (LootToKafra kafraLoot: kafraLootList) {
            interfaceActions.putItemToKafra(kafraLoot.markerInventoryPath,
                    kafraLoot.lootPath);
        }
        interfaceActions.closeInventory();
        interfaceActions.pressClose();
        SleepTime.sleep(5000);
    }
}
