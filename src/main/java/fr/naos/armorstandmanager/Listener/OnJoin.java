package fr.naos.armorstandmanager.Listener;

import fr.naos.armorstandmanager.ArmorstandManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp() && ArmorstandManager.getInstance().updateCheck()) {
            player.sendMessage("\n§a[ArmorStand Manager] §fA new version is available\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=\n§fYour version : §b" + ArmorstandManager.getInstance().getDescription().getVersion() + "\n§fDownload it here :§e https://www.spigotmc.org/resources/armorstand-manager.108524/\n§fIf you §clike §fthe plugin, please leave a review, it help a lot !\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=\n");
        }
    }
}
