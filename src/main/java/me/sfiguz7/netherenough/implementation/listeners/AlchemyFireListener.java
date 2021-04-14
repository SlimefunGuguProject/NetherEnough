package me.sfiguz7.netherenough.implementation.listeners;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.sfiguz7.netherenough.implementation.items.items.AlchemyFire;
import me.sfiguz7.netherenough.implementation.items.items.FireStarter;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.Optional;

import static org.bukkit.event.EventPriority.LOWEST;

public class AlchemyFireListener implements Listener {

    public AlchemyFireListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = LOWEST)
    public void onLight(@Nonnull PlayerRightClickEvent e) {
        ItemStack item = e.getItem();
        if (!(SlimefunItem.getByItem(item) instanceof FireStarter)) {
            Optional<Block> b = e.getClickedBlock();
            b.ifPresent(block -> {
                if (BlockStorage.check(block) instanceof AlchemyFire) {
                e.cancel();
            }});
        }
    }
}
