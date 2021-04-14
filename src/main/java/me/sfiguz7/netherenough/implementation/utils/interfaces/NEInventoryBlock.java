package me.sfiguz7.netherenough.implementation.utils.interfaces;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public interface NEInventoryBlock {

    int[] getInputSlots();

    int[] getOutputSlots();

    default void createPreset(@Nonnull SlimefunItem item, @Nonnull Consumer<BlockMenuPreset> setup) {
        String title = item.getItemName();
        new BlockMenuPreset(item.getId(), title) {
            public void init() {
                setup.accept(this);
            }

            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return flow == ItemTransportFlow.INSERT ? getInputSlots() : getOutputSlots();
            }

            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.INTERACT_BLOCK) && Slimefun.hasPermission(p, item, false);
            }
        };
    }
}