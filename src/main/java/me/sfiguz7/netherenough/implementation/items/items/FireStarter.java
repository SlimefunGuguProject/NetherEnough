package me.sfiguz7.netherenough.implementation.items.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.sfiguz7.netherenough.lists.NEItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class FireStarter extends SimpleSlimefunItem<ItemUseHandler> {

    public FireStarter() {
        super(NEItems.netherenough, NEItems.FIRE_STARTER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.FLINT), new ItemStack(Material.IRON_INGOT), null,
                new ItemStack(Material.WHEAT), new ItemStack(Material.GUNPOWDER), null,
                null, null, null}
        );
    }


    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Optional<Block> b = e.getClickedBlock();
            b.ifPresent(block -> {
                if (BlockStorage.check(block) instanceof AlchemyFire) {
                    Campfire campfire = (Campfire) block.getBlockData();
                    if (!campfire.isLit()) {
                        campfire.setLit(true);
                        block.setBlockData(campfire);
                        handleFlintAndSteel(e);
                    }
                }
            });
        };
    }

    void handleFlintAndSteel (PlayerRightClickEvent e) {
        ItemStack item = e.getItem();
        Damageable meta = (Damageable) item.getItemMeta();
        // Flint and steel durability is 64 or ~13*5. We want 5 uses.
        // Add 13 each time and break after the 5th time
        int dmg = meta.getDamage();
        dmg += 13;
        if (dmg > Material.FLINT_AND_STEEL.getMaxDurability()) {
            Player p = e.getPlayer();
            if (e.getHand() == EquipmentSlot.HAND) {
                p.getInventory().clear(p.getInventory().getHeldItemSlot());
            } else {
                // Offhand slot is 40
                p.getInventory().clear(40);
            }
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
        } else {
            meta.setDamage(dmg);
            item.setItemMeta((ItemMeta) meta);
        }
    }
}
