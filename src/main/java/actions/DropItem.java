package actions;

import main.Prop;

public class DropItem {
    private String pathInventory;
    private String pathLoot;

    public DropItem(String pathInventory, String pathLoot) {
        this.pathInventory = Prop.PATH_INVENTORY_MARKER +
                pathInventory + "frag.png";
        this.pathLoot = pathLoot;
    }
}
