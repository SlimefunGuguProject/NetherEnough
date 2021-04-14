package me.sfiguz7.netherenough;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
import me.sfiguz7.netherenough.commands.NECommands;
import me.sfiguz7.netherenough.enchantments.ShinyBoiEnchantment;
import me.sfiguz7.netherenough.implementation.items.items.AlchemyFire;
import me.sfiguz7.netherenough.implementation.items.items.FireStarter;
import me.sfiguz7.netherenough.implementation.items.items.ManaRod;
import me.sfiguz7.netherenough.implementation.items.items.Powder;
import me.sfiguz7.netherenough.implementation.items.machines.Alembic;
import me.sfiguz7.netherenough.implementation.listeners.AlchemyFireListener;
import me.sfiguz7.netherenough.implementation.listeners.IgnisBlockBreakListener;
import me.sfiguz7.netherenough.implementation.listeners.InfusedBlocksListener;
import me.sfiguz7.netherenough.implementation.listeners.NEPhantomKillListener;
import me.sfiguz7.netherenough.implementation.listeners.NEPhantomSpawnListener;
import me.sfiguz7.netherenough.implementation.listeners.NEWorldMobSpawnListener;
import me.sfiguz7.netherenough.generation.worlds.NEWorld;
import me.sfiguz7.netherenough.lists.Constants;
import me.sfiguz7.netherenough.lists.NEItems;
import me.sfiguz7.netherenough.lists.NERegistry;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        if (getConfig().getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "Sfiguz7/NetherEnough/master").start();
        }

        // Commands
        getCommand("netherenough").setExecutor(new NECommands());
        // Listeners
        new AlchemyFireListener(this);
        new IgnisBlockBreakListener(this);
        new InfusedBlocksListener(this);
        new NEPhantomKillListener(this);
        new NEPhantomSpawnListener(this);
        new NEWorldMobSpawnListener(this);
        // Config fetching
        manaRodChance = getConfig().getInt("options.manarod-infusing-chance");
        if (manaRodChance < 0 || manaRodChance > 100) {
            getLogger().log(Level.SEVERE, "Invalid config option: options.manarod-infusing-chance");
            getLogger().log(Level.SEVERE, "Chance must be an integer between 0 and 100");
            getServer().getPluginManager().disablePlugin(this);
        }

        //Worlds
        for (NEWorld.Type type : NEWorld.Type.values()) {
            new NEWorld(type).loadWorld();
        }

        // Items
        new ManaRod().register(this);
        new Research(new NamespacedKey(this, "unstable"),
            ++researchId, "Unstable", 23)
            .addItems(NEItems.MANA_ROD).register();
        new Alembic().register(this);
        try {
            if (!Enchantment.isAcceptingRegistrations()) {
                Field accepting = Enchantment.class.getDeclaredField("acceptingNew");
                accepting.setAccessible(true);
                accepting.set(null, true);
            }
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
            getLogger().warning("Failed to register enchantment. Seems the 'acceptingNew' field changed");
        }
        Enchantment.registerEnchantment(new ShinyBoiEnchantment(Constants.SHINY_BOI_ENCHANTMENT));
        for (Powder.Type type : Powder.Type.values()) {
            new Powder(type).register(this);
        }
        new FireStarter().register(this);
        for (AlchemyFire.Type type : AlchemyFire.Type.values()) {
            new AlchemyFire(type).register(this);
        }

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

    @Nonnull
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
