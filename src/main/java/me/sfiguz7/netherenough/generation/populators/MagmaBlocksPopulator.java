package me.sfiguz7.netherenough.generation.populators;

import me.sfiguz7.netherenough.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class MagmaBlocksPopulator extends BlockPopulator {

    @Override
    @ParametersAreNonnullByDefault
    public void populate(World world, Random random, Chunk chunk) {
        System.out.println("Magma at" + chunk.getX() + ", " + chunk.getZ());
        int count = 0;
        int x, y, z;
        boolean isMagma;
        for (int i = 1; i < 15; i++) {
            x = random.nextInt(15);
            z = random.nextInt(15);
            y = random.nextInt(17) + 100;
            if (world.getBlockAt(x, y, z).getType() == Material.RED_NETHER_BRICKS) {
                isMagma = true;
                while (isMagma) {
                    world.getBlockAt(x, y, z).setType(Material.MAGMA_BLOCK);
                    count++;
                    if (Utils.chance(90)) {
                        switch (random.nextInt(6)) {
                            case 0:
                                x++;
                                break;
                            case 1:
                                y++;
                                break;
                            case 2:
                                z++;
                                break;
                            case 3:
                                x--;
                                break;
                            case 4:
                                y--;
                                break;
                            case 5:
                                z--;
                                break;
                        }
                        isMagma = (world.getBlockAt(x, y, z).getType() == Material.MAGMA_BLOCK);
                    } else isMagma = false;
                }
            }
        }
        System.out.println("Generated " + count + " blocks");
    }
}

