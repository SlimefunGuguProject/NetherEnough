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

import static org.bukkit.event.EventPriority.LOWEST;

public class InfusedBlocksListener implements Listener {

    public InfusedBlocksListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = LOWEST)
    public void onBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Location loc = b.getLocation();
        if (NetherEnough.getRegistry().getInfusedBlocks().contains(loc)) {
            NetherEnough.getRegistry().getInfusedBlocks().remove(loc);
            e.getBlock().getWorld().dropItemNaturally(loc, getInfusedBlock(b.getType()));
        }
    }

    private ItemStack getInfusedBlock(Material m) {
        switch (m) {
            case CHISELED_NETHER_BRICKS:
                return new CustomItem(NEItems.DEMONIC_BRICKS);
            case CHISELED_STONE_BRICKS:
                return new CustomItem(NEItems.INFUSED_STONE);
            case CHISELED_QUARTZ_BLOCK:
                return new CustomItem(NEItems.DWARVEN_MARBLE);
            default:
                // Something else must have edited our block without breaking it
                // We'll simply drop whatever the block is right now
                return new ItemStack(m);
        }
    }

}
