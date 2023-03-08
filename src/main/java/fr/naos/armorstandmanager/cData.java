package fr.naos.armorstandmanager;

import org.bukkit.ChatColor;

public class cData {

    public static String prefix = ArmorstandManager.getInstance().getConfig().getString("prefix");
    public static String no_perm;
    public static String title_menu;
    public static String arm_text;
    public static String arm_lore_enable;
    public static String arm_lore_disable;
    public static String invulnerable_text;
    public static String invulnerable_lore_enable;
    public static String invulnerable_lore_disable;
    public static String set_pos_text;
    public static String set_pos_lore;
    public static String gravity_text;
    public static String gravity_lore_enable;
    public static String gravity_lore_disable;
    public static String baseplate_text;
    public static String baseplate_lore_enable;
    public static String baseplate_lore_disable;

    public static String lang = ArmorstandManager.getInstance().getConfig().getString("lang");
    public static void loadConfig() {
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        if (lang.equalsIgnoreCase("fr")) {

            no_perm = prefix + " &cVous n'avez pas la permission d'utiliser cette commande !";
            title_menu = "Gestion du porte armure";
            arm_text = "§aBras";
            arm_lore_enable = "§f    Cliquez pour §cdésactiver §fles bras";
            arm_lore_disable = "§f    Cliquez pour §aactiver §fles bras";
            invulnerable_text = "§aInvulnérabilité";
            invulnerable_lore_enable = "§f    Cliquez pour §cdésactiver §fl'invulnérabilité";
            invulnerable_lore_disable = "§f    Cliquez pour §aactiver §fl'invulnérabilité";
            set_pos_text = "§aPosture";
            set_pos_lore = "§f    Cliquez pour §echanger §fla posture du porte armure";
            gravity_text = "§aGravité";
            gravity_lore_enable = "§f    Cliquez pour §cdésactiver §fla gravité";
            gravity_lore_disable = "§f    Cliquez pour §aactiver §fla gravité";
            baseplate_text = "§aBase";
            baseplate_lore_enable = "§f    Cliquez pour §cdésactiver §fla base";
            baseplate_lore_disable = "§f    Cliquez pour §aactiver §fla base";

        } else if (lang.equalsIgnoreCase("en")) {

            no_perm = prefix + " &cYou don't have the permission to use this command !";
            title_menu = "Armorstand management";
            arm_text = "§aArms";
            arm_lore_enable = "§f    Click to §cdisable §fthe arms";
            arm_lore_disable = "§f    Click to §aenable §fthe arms";
            invulnerable_text = "§aInvulnerability";
            invulnerable_lore_enable = "§f    Click to §cdisable §fthe invulnerability";
            invulnerable_lore_disable = "§f    Click to §aenable §fthe invulnerability";
            set_pos_text = "§aPosture";
            set_pos_lore = "§f    Click to §echange §fthe posture of the armorstand";
            gravity_text = "§aGravity";
            gravity_lore_enable = "§f    Click to §cdisable §fthe gravity";
            gravity_lore_disable = "§f    Click to §aenable §fthe gravity";
            baseplate_text = "§aBase";
            baseplate_lore_enable = "§f    Click to §cdisable §fthe base";
            baseplate_lore_disable = "§f    Click to §aenable §fthe base";

        } else {
            ArmorstandManager.getInstance().getLogger().warning("The language is not valid, please change it in the config.yml");
            ArmorstandManager.getInstance().getPluginLoader().disablePlugin(ArmorstandManager.getInstance());
        }
    }
}
