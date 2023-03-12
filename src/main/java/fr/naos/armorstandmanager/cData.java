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
    public static String goAttack_text;
    public static String goAttack_lore;

    public static String allonger_text;
    public static String allonger_lore;
    public static String handInAir_text;
    public static String handInAir_lore;
    public static String swordInRock_text;
    public static String swordInRock_lore;
    public static String arrowLeft_text;
    public static String arrowLeft_lore;
    public static String arrowRight_text;
    public static String arrowRight_lore;

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
            goAttack_text = "§aA l'attaque !";
            goAttack_lore = "§f    Cliquez pour §emettre §fle porte armure en position attaque";
            allonger_text = "§aAllonger";
            allonger_lore = "§f    Cliquez pour §eallonger §fle porte armure";
            handInAir_text = "§aMain en l'air";
            handInAir_lore = "§f    Cliquez pour §emettre §fle porte armure en position main en l'air";
            swordInRock_text = "§aEpée dans la roche";
            swordInRock_lore = "§f    Cliquez pour §emettre §fle porte armure en position épée dans la roche";
            arrowLeft_text = "§aTourner vers la gauche";
            arrowLeft_lore = "§f    Cliquez pour §etourner §fle porte armure vers la gauche";
            arrowRight_text = "§aTourner vers la droite";
            arrowRight_lore = "§f    Cliquez pour §etourner §fle porte armure vers la droite";


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
            goAttack_text = "§aGo attack !";
            goAttack_lore = "§f    Click to §eput §fthe armorstand in attack position";
            allonger_text = "§aStretch";
            allonger_lore = "§f    Click to §estretch §fthe armorstand";
            handInAir_text = "§aHand in the air";
            handInAir_lore = "§f    Click to §eput §fthe armorstand in hand in the air position";
            swordInRock_text = "§aSword in the rock";
            swordInRock_lore = "§f    Click to §eput §fthe armorstand in sword in the rock position";
            arrowLeft_text = "§aTurn to the left";
            arrowLeft_lore = "§f    Click to §eturn §fthe armorstand to the left";
            arrowRight_text = "§aTurn to the right";
            arrowRight_lore = "§f    Click to §eturn §fthe armorstand to the right";

        } else {
            ArmorstandManager.getInstance().getLogger().warning("The language is not valid, please change it in the config.yml");
            ArmorstandManager.getInstance().getPluginLoader().disablePlugin(ArmorstandManager.getInstance());
        }
    }
}
