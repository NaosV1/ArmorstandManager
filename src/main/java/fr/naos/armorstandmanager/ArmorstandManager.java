package fr.naos.armorstandmanager;

import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import com.jeff_media.updatechecker.UserAgentBuilder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorstandManager extends JavaPlugin {

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

    }

    public static ArmorstandManager getInstance() {
        return instance;
    }
}
