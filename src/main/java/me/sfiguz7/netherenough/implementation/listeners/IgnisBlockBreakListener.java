package me.sfiguz7.netherenough.implementation.listeners;

import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.sfiguz7.netherenough.NetherEnough;
import me.sfiguz7.netherenough.lists.NEItems;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

import static org.bukkit.event.EventPriority.LOWEST;

public class IgnisBlockBreakListener implements Listener {

    public IgnisBlockBreakListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = LOWEST)
    public void onBreak(@Nonnull BlockBreakEvent e) {
        Block b = e.getBlock();
        Location loc = b.getLocation();
        if (NetherEnough.getRegistry().getIgnisBlocks().contains(loc)) {
            NetherEnough.getRegistry().getIgnisBlocks().remove(loc);
            e.setDropItems(false);
            e.getBlock().getWorld().dropItemNaturally(loc, new CustomItem(NEItems.IGNIS));
        }
    }
}
