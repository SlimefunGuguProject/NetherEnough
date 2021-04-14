package me.sfiguz7.netherenough.generation.populators;

import me.sfiguz7.netherenough.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class CryingObsidianPopulator extends BlockPopulator {

    @Override
    @ParametersAreNonnullByDefault
    public void populate(World world, Random random, Chunk chunk) {
        int x, y, z;
        boolean isObsidian;
        for (int i = 1; i < 15; i++) {
            if (Utils.chance(95)) {
                x = random.nextInt(15);
                z = random.nextInt(15);
                y = random.nextInt(7) + 20;
                if (world.getBlockAt(x, y, z).getType() == Material.OBSIDIAN) {
                    isObsidian = true;
                    while (isObsidian) {
                        world.getBlockAt(x, y, z).setType(Material.CRYING_OBSIDIAN);
                        if (Utils.chance(97.5)) {
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
                            isObsidian = (world.getBlockAt(x, y, z).getType() == Material.OBSIDIAN);
                        } else isObsidian = false;
                    }
                }
            }
        }
    }
}
