package me.sfiguz7.netherenough.generation.worlds;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NiflheimGenerator extends ChunkGenerator {

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomeGrid) {
        int height;
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.01D);

        final int chunkRealX = chunkX << 4;
        final int chunkRealZ = chunkZ << 4;
        int x;
        int z;

        for (int relx = 0; relx < 16; relx++)
            for (int relz = 0; relz < 16; relz++) {
                x = chunkRealX + relx;
                z = chunkRealZ + relz;
                chunk.setBlock(relx, 0, relz, Material.BEDROCK);
                height = (int) (60D + 30 * generator.noise(x, z, 0.5D, 0.5D));
                chunk.setBlock(relx, height, relz, Material.QUARTZ_BLOCK);
                chunk.setBlock(relx, height - 1, relz, Material.DIRT);
                for (int i = height - 2; i > 0; i--) {
                    chunk.setBlock(relx, i, relz, Material.STONE);
                }
            }

        return chunk;
    }

    @Override
    @Nonnull
    public List<BlockPopulator> getDefaultPopulators(@Nonnull World world) {
        List<BlockPopulator> blockPopulatorList = new ArrayList<>();
        return blockPopulatorList;
    }
}
