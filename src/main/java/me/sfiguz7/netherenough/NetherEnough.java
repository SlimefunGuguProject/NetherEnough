package me.sfiguz7.netherenough;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
import me.sfiguz7.netherenough.implementation.items.items.ManaRod;
import me.sfiguz7.netherenough.implementation.listeners.InfusedBlocksListener;
import me.sfiguz7.netherenough.lists.NEItems;
import me.sfiguz7.netherenough.lists.NERegistry;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class NetherEnough extends JavaPlugin implements SlimefunAddon {

    private static NetherEnough instance;
    private final NERegistry registry = new NERegistry();
    private int researchId = 7700;

    private int manaRodChance;

    @Override
    public void onEnable() {
        instance = this;

        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }

        Config cfg = new Config(this);

        if (getConfig().getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "Sfiguz7/NetherEnough/master").start();
        }

        //Listeners
        new InfusedBlocksListener(this);
        // Config fetching
        manaRodChance = getConfig().getInt("options.manarod-infusing-chance");
        if (manaRodChance < 0 || manaRodChance > 100) {
            getLogger().log(Level.SEVERE, "Invalid config option: options.manarod-infusing-chance");
            getLogger().log(Level.SEVERE, "Chance must be an integer between 0 and 100");
            getServer().getPluginManager().disablePlugin(this);
        }

        new ManaRod().register(this);
        new Research(new NamespacedKey(this, "unstable"),
            ++researchId, "Unstable", 23)
            .addItems(NEItems.MANA_ROD).register();

    }

    @Override
    public void onDisable() {
        instance = null;

        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Sfiguz7/NetherEnough/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static NetherEnough getInstance() {
        return instance;
    }

    public static NERegistry getRegistry() {
        return instance.registry;
    }

    public static String getVersion() {
        return instance.getDescription().getVersion();
    }

    public int getManaRodChance() {
        return instance.manaRodChance;
    }

}
