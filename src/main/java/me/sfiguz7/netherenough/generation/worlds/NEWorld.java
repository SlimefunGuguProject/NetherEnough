package me.sfiguz7.netherenough.generation.worlds;

import me.sfiguz7.netherenough.NetherEnough;
import me.sfiguz7.netherenough.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.ChunkGenerator;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NEWorld {

    private final String name;
    private final World.Environment environment;
    private final ChunkGenerator generator;
    private final EntityType nativeEntity;
    private final Logger logger = NetherEnough.getInstance().getLogger();
    private World world;
    private UUID uuid;

    public NEWorld(@Nonnull Type type) {
        this.name = type.name;
        this.environment = type.environment;
        this.generator = type.generator;
        this.nativeEntity = type.nativeEntity;
    }

    public void loadWorld() {
        World bukWorld = Bukkit.getWorld(uuid);
        if (bukWorld == null) {
            String message = "Generating a new ne_" + name + " world, this may take a while...";
            logger.log(Level.INFO, message);
            bukWorld = new WorldCreator("ne_" + name).generator(generator).environment(environment).createWorld();
            if (bukWorld == null) {
                logger.log(Level.SEVERE, "An unexpected error caused world generation to fail!");
            } else {
                world = bukWorld;
                uuid = bukWorld.getUID();

                NetherEnough.getRegistry().getNeWorlds().put(uuid, this);
                logger.log(Level.INFO, "World successfully loaded!");
            }
        }
    }

    public World getWorld() {
        return world;
    }

    public EntityType getSpawningEntity() {
        if (Utils.chance(50)) {
            return EntityType.PHANTOM;
        } else {
            return nativeEntity;
        }
    }

    public enum Type {
        MUSPELHEIM("muspelheim", World.Environment.NETHER, new MuspelheimGenerator(), EntityType.HOGLIN
        ),
        NIFLHEIM("niflheim", World.Environment.NETHER, new NiflheimGenerator(), EntityType.POLAR_BEAR
        );

        private final String name;
        private final World.Environment environment;
        private final ChunkGenerator generator;
        private final EntityType nativeEntity;

        Type(String name, World.Environment environment, ChunkGenerator generator, EntityType nativeEntity) {
            this.name = name;
            this.environment = environment;
            this.generator = generator;
            this.nativeEntity = nativeEntity;
        }
    }

}
