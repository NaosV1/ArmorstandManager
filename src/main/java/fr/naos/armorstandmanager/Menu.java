package fr.naos.armorstandmanager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.AIR;

public class Menu implements Listener {


    public static Map<Player, ArmorStand> playerArmorStandMap = new HashMap<>();


    public static void manage(Player player) {
        if (!(player.hasPermission("asmanager.custominteract"))) { player.sendMessage(cData.no_perm);}
        Inventory inventory = org.bukkit.Bukkit.createInventory(new MenuHolder(), 5 * 9, cData.title_menu);
        initaliseItem(player, inventory);
        player.openInventory(inventory);
    }

    private static void initaliseItem(Player player, Inventory inventory) {
        ArmorStand armorstand = playerArmorStandMap.get(player);
        inventory.setItem(11, ItemManage.createItem(armorstand.hasArms(), Material.ARROW, cData.arm_text, 1, (armorstand.hasArms() ? cData.arm_lore_enable : cData.arm_lore_disable)));
        inventory.setItem(13, ItemManage.createItem(armorstand.isInvulnerable(), Material.ENCHANTED_GOLDEN_APPLE, cData.invulnerable_text, 1, (armorstand.isInvulnerable() ? cData.invulnerable_lore_enable : cData.invulnerable_lore_disable)));
        inventory.setItem(15, ItemManage.createItem(false, Material.LADDER, cData.set_pos_text, 1, cData.set_pos_lore));
        inventory.setItem(30, ItemManage.createItem(armorstand.hasGravity(), Material.BARRIER, cData.gravity_text, 1, (armorstand.hasGravity() ? cData.gravity_lore_enable : cData.gravity_lore_disable)));
        inventory.setItem(32, ItemManage.createItem(armorstand.hasBasePlate(), Material.ARMOR_STAND, cData.baseplate_text, 1, (armorstand.isVisible() ? cData.baseplate_lore_enable : cData.baseplate_lore_disable)));
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, ItemManage.createItem(false, Material.GRAY_STAINED_GLASS_PANE, " ", 1));
            }
        }
    }

    @EventHandler
    private void onItemClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
            ArmorStand armorstandChanged = playerArmorStandMap.get(player);
        if (inv.getHolder() instanceof MenuHolder && armorstandChanged != null) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == AIR) return;
            if (event.getCurrentItem().getType() != Material.GRAY_STAINED_GLASS_PANE) {
                if (event.getCurrentItem().getType() == Material.ARROW && event.getSlot() == 11) {
                    armorstandChanged.setArms(!armorstandChanged.hasArms());
                    initaliseItem(player, inv);
                } else if (event.getCurrentItem().getType() == Material.ENCHANTED_GOLDEN_APPLE && event.getSlot() == 13) {
                    armorstandChanged.setInvulnerable(!armorstandChanged.isInvulnerable());
                    initaliseItem(player, inv);
                } else if (event.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE && event.getSlot() == 15) {
                    player.sendMessage(ChatColor.GREEN + "IN DEV");
                } else if (event.getCurrentItem().getType() == Material.BARRIER && event.getSlot() == 30) {
                    armorstandChanged.setGravity(!armorstandChanged.hasGravity());
                    initaliseItem(player, inv);
                } else if (event.getCurrentItem().getType() == Material.ARMOR_STAND && event.getSlot() == 32) {
                    armorstandChanged.setBasePlate(!armorstandChanged.isVisible());
                    initaliseItem(player, inv);
                }
                player.setItemOnCursor(null);
            }
        }
    }
}
