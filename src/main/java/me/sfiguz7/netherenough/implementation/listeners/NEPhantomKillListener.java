package me.sfiguz7.netherenough.implementation.listeners;

import me.sfiguz7.netherenough.utils.Utils;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

import static org.bukkit.event.EventPriority.LOWEST;

public class NEPhantomKillListener implements Listener {

    public NEPhantomKillListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = LOWEST)
    public void onPhantomHit(@Nonnull EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        World world = entity.getLocation().getWorld();
        if (Utils.isNEWorld(world)
            && entity.getType() == EntityType.PHANTOM) {
            e.setCancelled(true);
        }
    }
}
