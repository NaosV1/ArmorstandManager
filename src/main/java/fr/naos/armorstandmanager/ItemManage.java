package fr.naos.armorstandmanager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemManage {
    public static ItemStack createItem(boolean enchanted, final Material material, final String name, final int quantity, final String... lore) {
        final ItemStack item = new ItemStack(material, quantity);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        if (enchanted) {
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true);
            meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);

        return item;
    }
}
