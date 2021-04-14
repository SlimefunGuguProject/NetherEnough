package me.sfiguz7.netherenough.generation.worlds;

import me.sfiguz7.netherenough.generation.populators.CoalBlocksPopulator;
import me.sfiguz7.netherenough.generation.populators.CryingObsidianPopulator;
import me.sfiguz7.netherenough.generation.populators.MagmaBlocksPopulator;
import me.sfiguz7.netherenough.generation.populators.NetheriteBlocksPopulator;
import me.sfiguz7.netherenough.generation.populators.RedstoneBlocksPopulator;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MuspelheimGenerator extends ChunkGenerator {

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomeGrid) {
        int layerOneTop;
        int layerTwoBot;
        int layerTwoTop;

        ChunkData chunk = createChunkData(world);

        // 3 Randoms kinda heavy, see if it can be improved
        SimplexOctaveGenerator generatorOne = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        SimplexOctaveGenerator generatorTwoBot = new SimplexOctaveGenerator(new Random(world.getSeed() + 1), 8);
        SimplexOctaveGenerator generatorTwoTop = new SimplexOctaveGenerator(new Random(world.getSeed() + 2), 8);

        generatorOne.setScale(0.01D);
        generatorTwoBot.setScale(0.01D);
        generatorTwoTop.setScale(0.01D);

        final int chunkRealX = chunkX << 4;
        final int chunkRealZ = chunkZ << 4;
        int x;
        int z;

        for (int relx = 0; relx < 16; relx++)
            for (int relz = 0; relz < 16; relz++) {
                x = chunkRealX + relx;
                z = chunkRealZ + relz;

                layerOneTop = (int) (20D + 7 * generatorOne.noise(x, z, 0.5D, 0.5D));
                layerTwoBot = (int) (70D + 10 * generatorOne.noise(x, z, 0.5D, 0.5D));
                layerTwoTop = (int) (110D + 7 * generatorOne.noise(x, z, 0.5D, 0.5D));

                chunk.setBlock(relx, 0, relz, Material.BEDROCK);

                for (int i = 1; i < layerOneTop; i++) {
                    chunk.setBlock(relx, i, relz, Material.OBSIDIAN);
                }
                for (int i = layerTwoBot; i < layerTwoTop; i++) {
                    chunk.setBlock(relx, i, relz, Material.RED_NETHER_BRICKS);
                }
            }

        return chunk;
    }

    @Override
    @Nonnull
    public List<BlockPopulator> getDefaultPopulators(@Nonnull World world) {
        return Arrays.asList(
            new CryingObsidianPopulator(),
            new NetheriteBlocksPopulator(),
            new CoalBlocksPopulator(),
            new RedstoneBlocksPopulator(),
            new MagmaBlocksPopulator()
        );
    }
}
