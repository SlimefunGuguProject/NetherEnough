package me.sfiguz7.netherenough.generation.populators;

import me.sfiguz7.netherenough.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class CoalBlocksPopulator extends BlockPopulator {

    @Override
    @ParametersAreNonnullByDefault
    public void populate(World world, Random random, Chunk chunk) {
        int x, y, z;
        boolean isMagma;
        for (int i = 1; i < 15; i++) {
            if (Utils.chance(90)) {
                x = random.nextInt(15);
                z = random.nextInt(15);
                y = random.nextInt(17) + 90;
                if (world.getBlockAt(x, y, z).getType() == Material.RED_NETHER_BRICKS) {
                    isMagma = true;
                    while (isMagma) {
                        world.getBlockAt(x, y, z).setType(Material.COAL_BLOCK);
                        if (Utils.chance(80)) {
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
                            isMagma = (world.getBlockAt(x, y, z).getType() == Material.RED_NETHER_BRICKS);
                        } else isMagma = false;
                    }
                }
            }
        }
    }
}
