package fr.naos.armorstandmanager.Listener;

import fr.naos.armorstandmanager.Utils.Menu;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ArmorstandListener implements Listener {

    @EventHandler
    public void onArmorstandInteract(PlayerInteractAtEntityEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;
        Player player = event.getPlayer();
        if (event.isCancelled()) return;
        if (player.hasPermission("asmanager.custominteract")) {
            if (player.isSneaking()){
                if (event.getRightClicked() instanceof ArmorStand) {
                    event.setCancelled(true);
                    ArmorStand armorstand = (ArmorStand) event.getRightClicked();
                    Menu.playerArmorStandMap.put(player, armorstand);
                    Menu.manage(player);
                }
            }
        }
    }

}
