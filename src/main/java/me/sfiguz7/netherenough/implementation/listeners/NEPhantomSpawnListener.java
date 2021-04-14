package me.sfiguz7.netherenough.implementation.listeners;

import me.mrCookieSlime.Slimefun.cscorelib2.chat.ChatColors;
import me.sfiguz7.netherenough.utils.Utils;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

import static org.bukkit.event.EventPriority.LOWEST;

public class NEPhantomSpawnListener implements Listener {

    public NEPhantomSpawnListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = LOWEST)
    public void onPhantomSpawn(@Nonnull EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        World world = entity.getWorld();
        if (Utils.isNEWorld(world)
            && entity.getType() == EntityType.PHANTOM) {
            entity.setCustomName(ChatColors.color("&l&5Wandering Soul"));
        }
    }
}

