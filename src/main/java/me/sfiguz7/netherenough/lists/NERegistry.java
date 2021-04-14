package me.sfiguz7.netherenough.lists;

import me.sfiguz7.netherenough.generation.worlds.NEWorld;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NERegistry {

    private final Map<UUID, NEWorld> neWorlds = new HashMap<>();
    private final Set<Location> infusedBlocks = new HashSet<>();
    private final Set<Location> ignisBlocks = new HashSet<>();

    public Map<UUID, NEWorld> getNeWorlds() { return neWorlds; }
    public Set<Location> getInfusedBlocks() {
        return infusedBlocks;
    }
    public Set<Location> getIgnisBlocks() {
        return ignisBlocks;
    }

}
