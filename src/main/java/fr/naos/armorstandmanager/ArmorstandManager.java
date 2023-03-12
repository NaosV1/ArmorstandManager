package fr.naos.armorstandmanager;


import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class ArmorstandManager extends JavaPlugin {
    private int resourceId;
    public static ArmorstandManager instance;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ArmorstandListener(), this);
        pm.registerEvents(new Menu(), this);
        Metrics metrics = new Metrics(this, 17899);
        cData.loadConfig();
        updateCheck();



    }

    private void updateCheck(){
        resourceId = 108424;
        try {
            URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=108524");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            if (!(content.toString().equals(getDescription().getVersion()))) {
                Bukkit.getConsoleSender().sendMessage("\n§a[ArmorStand Manager] §fA new version is available\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=\n§fYour version : §b" + getDescription().getVersion() + "\n§fNew version : §b" + content + "\n§fDownload it here :§e https://www.spigotmc.org/resources/armorstand-manager.108524/\n§fIf you §clike §fthe plugin, please leave a review, it help a lot !\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=\n");
            }
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§cError checking update for ArmorStand Manager: §e" + e.getMessage());
        }

    }

    public static ArmorstandManager getInstance() {
        return instance;
    }
}
