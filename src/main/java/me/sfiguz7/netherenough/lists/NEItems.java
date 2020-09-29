package me.sfiguz7.netherenough.lists;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.sfiguz7.netherenough.NetherEnough;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class NEItems {

    private NEItems() {
    }

    /* Category */
    public static final Category neverenough = new Category(new NamespacedKey(NetherEnough.getInstance(), "netherenough"),
        new CustomItem(Material.CHISELED_NETHER_BRICKS, "&cNetherEnough")
    );

    /* Items */
    public static final SlimefunItemStack MANA_ROD = new SlimefunItemStack("MANA_ROD",
        Material.END_ROD,
        "&bMana Rod",
        "&9Infuses nearby blocks, bringing",
        "&9their intrinsic nature to light"
    );
    public static final SlimefunItemStack DEMONIC_BRICKS = new SlimefunItemStack("DEMONIC_BRICKS",
        Material.CHISELED_NETHER_BRICKS,
        "&4Demonic Bricks",
        "&9It is said they contain",
        "&9the soul of a demon"
    );
    public static final SlimefunItemStack INFUSED_STONE = new SlimefunItemStack("INFUSED_STONE",
        Material.CHISELED_STONE_BRICKS,
        "&bInfused Stone",
        "&9Mana imbued stone, great",
        "&9structural integrity"
    );
    public static final SlimefunItemStack DWARVEN_MARBLE = new SlimefunItemStack("DWARVEN_MARBLE",
        Material.CHISELED_QUARTZ_BLOCK,
        "&bDwarven Marble",
        "&9A marvelous material"
    );
}