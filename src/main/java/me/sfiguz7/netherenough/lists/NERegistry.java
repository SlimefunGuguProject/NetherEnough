package me.sfiguz7.netherenough.lists;

import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

public class NERegistry {

    private final Set<Location> infusedBlocks = new HashSet<>();

    public Set<Location> getInfusedBlocks() {
        return infusedBlocks;
    }

}
