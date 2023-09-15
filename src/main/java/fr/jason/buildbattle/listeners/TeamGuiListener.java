package fr.jason.buildbattle.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamGuiListener implements Listener {

    Inventory inv;

    public Inventory TeamGUI(){
        inv = Bukkit.createInventory(null, 9, "Teams");

        inizializeItems();

        return inv;
    }

    public void inizializeItems(){
        inv.addItem(createGuiItem(Material.RED_WOOL, "§cTeam Rouge"));
        inv.addItem(createGuiItem(Material.BLUE_WOOL, "§9Team Bleue"));
        inv.addItem(createGuiItem(Material.YELLOW_WOOL, "§eTeam Jaune"));
        inv.addItem(createGuiItem(Material.LIME_WOOL, "§aTeam Verte"));
    }

    protected ItemStack createGuiItem(final Material material, final String name){

        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        item.setItemMeta(meta);

        return item;

    }
}
