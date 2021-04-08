package me.sfiguz7.netherenough.implementation.items.items;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.sfiguz7.netherenough.NetherEnough;
import me.sfiguz7.netherenough.lists.NEItems;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class ManaRod extends SimpleSlimefunItem<BlockTicker> {

    public ManaRod() {
        super(NEItems.netherenough, NEItems.MANA_ROD, RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {null, SlimefunItems.MAGIC_LUMP_1, null,
                null, null, null,
                null, null, null}
                );
    }


    @Override
    public BlockTicker getItemHandler() {
        return new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {

                // Check neighbours to transform
                // We check the type of the block, no need to skip the center in our loops
                // It will be automatically skipped by the type check
                for (int z = b.getZ() - 1; z <= b.getZ() + 1; z++) {
                    for (int x = b.getX() - 1; x <= b.getX() + 1; x++) {
                        World world = b.getWorld();
                        Block b2 = world.getBlockAt(x, b.getY(), z);
                        Material mat = getInfusedMaterial(b2.getType());
                        if (mat != null) {
                            int index = ThreadLocalRandom.current().nextInt(100);
                            if (index < NetherEnough.getInstance().getManaRodChance()) {
                                Location loc = b2.getLocation();
                                world.playSound(loc, Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 20, 3);
                                world.spawnParticle(Particle.ENCHANTMENT_TABLE, loc.add(0.5, 1.5, 0.5), 16, 0.3F,
                                    0.2F, 0.3F);
                                b2.setType(mat);
                                NetherEnough.getRegistry().getInfusedBlocks().add(b2.getLocation());
                            }
                        }
                    }
                }
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        };
    }

    private Material getInfusedMaterial(Material m) {
        switch (m) {
            case NETHER_BRICKS:
                return Material.CHISELED_NETHER_BRICKS;
            case STONE_BRICKS:
                return Material.CHISELED_STONE_BRICKS;
            case QUARTZ_BLOCK:
                return Material.CHISELED_QUARTZ_BLOCK;
            default:
                return null;
        }
    }

}



