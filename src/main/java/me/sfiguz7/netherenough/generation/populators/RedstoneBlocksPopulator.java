package me.sfiguz7.netherenough.generation.populators;

import me.sfiguz7.netherenough.NetherEnough;
import me.sfiguz7.netherenough.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class RedstoneBlocksPopulator extends BlockPopulator {

    @Override
    @ParametersAreNonnullByDefault
    public void populate(World world, Random random, Chunk chunk) {
        int x, y, z;
        for (int i = 1; i < 15; i++) {
            if (Utils.chance(80)) {
                x = random.nextInt(15);
                z = random.nextInt(15);
                y = random.nextInt(7) + 20;
                Block b = world.getBlockAt(x, y, z);
                if (b.getType() == Material.OBSIDIAN) {
                    world.getBlockAt(x, y, z).setType(Material.REDSTONE_BLOCK);
                    NetherEnough.getRegistry().getIgnisBlocks().add(b.getLocation());
                }
            }
        }
    }
}

