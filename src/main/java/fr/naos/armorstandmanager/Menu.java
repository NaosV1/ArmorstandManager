package fr.naos.armorstandmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        inventory.setItem(32, ItemManage.createItem(armorstand.hasBasePlate(), Material.ARMOR_STAND, cData.baseplate_text, 1, (armorstand.hasBasePlate() ? cData.baseplate_lore_enable : cData.baseplate_lore_disable)));

        ItemStack arrowHeadRight = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta metaRight = (SkullMeta) arrowHeadRight.getItemMeta();
        metaRight.setDisplayName(cData.arrowRight_text);
        metaRight.setLore(Collections.singletonList(cData.arrowRight_lore));
        metaRight.setOwningPlayer(Bukkit.getOfflinePlayer("MHF_ArrowRight"));
        arrowHeadRight.setItemMeta(metaRight);
        inventory.setItem(26, arrowHeadRight);

        ItemStack arrowHeadLeft = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta metaLeft = (SkullMeta) arrowHeadLeft.getItemMeta();
        metaLeft.setDisplayName(cData.arrowLeft_text);
        metaLeft.setLore(Collections.singletonList(cData.arrowLeft_lore));
        metaLeft.setOwningPlayer(Bukkit.getOfflinePlayer("MHF_ArrowLeft"));
        arrowHeadLeft.setItemMeta(metaLeft);
        inventory.setItem(18, arrowHeadLeft);

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, ItemManage.createItem(false, Material.GRAY_STAINED_GLASS_PANE, " ", 1));
            }
        }
    }

    public static void positionManage(Player player) {
        if (!(player.hasPermission("asmanager.custominteract"))) { player.sendMessage(cData.no_perm);}
        Inventory inventory = org.bukkit.Bukkit.createInventory(new MenuHolder(), 3 * 9, cData.title_menu);
        posInitaliseItem(inventory);
        player.openInventory(inventory);
    }

    private static void posInitaliseItem(Inventory inventory) {
        inventory.setItem(10, ItemManage.createItem(false, Material.IRON_SWORD, cData.goAttack_text, 1, cData.goAttack_lore));
        inventory.setItem(12, ItemManage.createItem(false, Material.RED_BED, cData.allonger_text, 1, cData.allonger_lore));
        inventory.setItem(14, ItemManage.createItem(false, Material.ARROW, cData.handInAir_text, 1, cData.handInAir_lore));
        inventory.setItem(16, ItemManage.createItem(false, Material.STONE, cData.swordInRock_text, 1, cData.swordInRock_lore));
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
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.GRAY_STAINED_GLASS_PANE) {
                Location loc = armorstandChanged.getLocation();
                if (event.getCurrentItem().getType() == Material.ARROW && event.getSlot() == 11) {
                    armorstandChanged.setArms(!armorstandChanged.hasArms());
                    initaliseItem(player, inv);
                } else if (event.getCurrentItem().getType() == Material.ENCHANTED_GOLDEN_APPLE && event.getSlot() == 13) {
                    armorstandChanged.setInvulnerable(!armorstandChanged.isInvulnerable());
                    initaliseItem(player, inv);
                } else if (event.getCurrentItem().getType() == Material.LADDER && event.getSlot() == 15) {
                    player.closeInventory();
                    positionManage(player);
                } else if (event.getCurrentItem().getType() == Material.BARRIER && event.getSlot() == 30) {
                    armorstandChanged.setGravity(!armorstandChanged.hasGravity());
                    initaliseItem(player, inv);
                } else if (event.getCurrentItem().getType() == Material.ARMOR_STAND && event.getSlot() == 32) {
                    armorstandChanged.setBasePlate(!armorstandChanged.hasBasePlate());
                    initaliseItem(player, inv);
                } else if (event.getCurrentItem().getType() == Material.PLAYER_HEAD && event.getSlot() == 26) {
                    armorstandChanged.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw() - 25, loc.getPitch()));
                    initaliseItem(player, inv);
                } else if (event.getCurrentItem().getType() == Material.PLAYER_HEAD && event.getSlot() == 18) {
                    armorstandChanged.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw() + 25, loc.getPitch()));
                    initaliseItem(player, inv);
                }
                player.setItemOnCursor(null);
            }
        }
    }

    @EventHandler
    private void onPoseClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ArmorStand armorstandChanged = playerArmorStandMap.get(player);
        if (inv.getHolder() instanceof MenuHolder && armorstandChanged != null) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.GRAY_STAINED_GLASS_PANE) {
                if (event.getCurrentItem().getType() == Material.IRON_SWORD && event.getSlot() == 10) {
                    armorStandPos(armorstandChanged, "goAttack");
                } else if (event.getCurrentItem().getType() == Material.RED_BED && event.getSlot() == 12) {
                    armorStandPos(armorstandChanged, "allonger");
                } else if (event.getCurrentItem().getType() == Material.ARROW && event.getSlot() == 14) {
                    armorStandPos(armorstandChanged, "handInAir");
                } else if (event.getCurrentItem().getType() == Material.STONE && event.getSlot() == 16) {
                    armorStandPos(armorstandChanged, "swordInRock");
                }
                player.setItemOnCursor(null);
            }
        }
    }

    private void armorStandPos(ArmorStand armorStand, String position) {

        armorStand.setArms(true);
        switch (position) {
            case "goAttack":
                armorStand.setHeadPose(new EulerAngle(0 ,0, 0));
                armorStand.setBodyPose(new EulerAngle(0, 0, 0));
                armorStand.setLeftArmPose(new EulerAngle(120, 0, 0));
                armorStand.setLeftLegPose(new EulerAngle(120, 0, 0));
                armorStand.setRightArmPose(new EulerAngle(280, 0, 0));
                armorStand.setRightLegPose(new EulerAngle(-170, 0, 0));
                break;
            case "allonger":
                armorStand.setHeadPose(new EulerAngle(-0.5 ,0, 0));
                armorStand.setBodyPose(new EulerAngle(-0.3, 0, 0));
                armorStand.setLeftArmPose(new EulerAngle(164, 1, 0));
                armorStand.setLeftLegPose(new EulerAngle(300, 0, 0));
                armorStand.setRightArmPose(new EulerAngle(164, -1, 0));
                armorStand.setRightLegPose(new EulerAngle(300, 0, 0));
                break;
            case "handInAir":
                armorStand.setHeadPose(new EulerAngle(0 ,0, 0));
                armorStand.setBodyPose(new EulerAngle(0, 0, 0));
                armorStand.setLeftArmPose(new EulerAngle(280, 0, 0.5));
                armorStand.setLeftLegPose(new EulerAngle(0, 0, 0));
                armorStand.setRightArmPose(new EulerAngle(280, 0, -0.5));
                armorStand.setRightLegPose(new EulerAngle(0, 0, 0));
                break;
            case "swordInRock":
                armorStand.setHeadPose(new EulerAngle(0 ,0, 0));
                armorStand.setBodyPose(new EulerAngle(0, 0, 0));
                armorStand.setLeftArmPose(new EulerAngle(4.5, -0.3, -3));
                armorStand.setLeftLegPose(new EulerAngle(0, 0, 0));
                armorStand.setRightArmPose(new EulerAngle(4.5, 0.3, 3));
                armorStand.setRightLegPose(new EulerAngle(-0, 0, 0));
                break;
        }
    }
}
