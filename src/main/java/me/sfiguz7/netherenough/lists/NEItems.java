package me.sfiguz7.netherenough.lists;

import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.sfiguz7.netherenough.NetherEnough;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class NEItems {

    private NEItems() {
    }

    /* Category */
    public static final Category netherenough = new Category(new NamespacedKey(NetherEnough.getInstance(), "netherenough"),
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
    public static final SlimefunItemStack ALEMBIC = new SlimefunItemStack("ALEMBIC",
        Material.BREWING_STAND,
        "&cAlembic",
        "&9Extracts shit from other shit"
    );
    public static final SlimefunItemStack IGNIS = new SlimefunItemStack("IGNIS",
        Material.RED_DYE,
        "&4Ignis",
        "&9Hawt"
    );
    public static final SlimefunItemStack AQUA = new SlimefunItemStack("AQUA",
        Material.BLUE_DYE,
        "&1Aqua",
        "&9Blub"
    );
    public static final SlimefunItemStack TERRA = new SlimefunItemStack("TERRA",
        Material.GREEN_DYE,
        "&2Terra",
        "&9I am Groot"
    );
    public static final SlimefunItemStack FULGUR = new SlimefunItemStack("FULGUR",
        Material.YELLOW_DYE,
        "&eFulgur",
        "&9Bzzt"
    );
    public static final SlimefunItemStack AURA = new SlimefunItemStack("AURA",
        Material.WHITE_DYE,
        "&7Aura",
        "&9Swishh"
    );
    public static final SlimefunItemStack ARCANE_POWDER = new SlimefunItemStack("ARCANE_POWDER",
        Material.BROWN_DYE,
        "&cArcane Powder",
        "&9Majik"
    );
    public static final SlimefunItemStack FIRE_STARTER = new SlimefunItemStack("FIRE_STARTER",
        Material.FLINT_AND_STEEL,
        "&cFire Starter",
        "&9Rudimental tool, can lit",
        "&9Antar/Bella Fires to",
        "&9low temperatures"
    );
    public static final SlimefunItemStack ANTAR_FIRE = new SlimefunItemStack("ANTAR_FIRE",
        Material.CAMPFIRE,
        "&cAntar Fire",
        "&9Enchanted flames act as",
        "&9catalysts. The flame color",
        "&9indicates temperature"
    );
    public static final SlimefunItemStack BELLA_FIRE = new SlimefunItemStack("BELLA_FIRE",
        Material.SOUL_CAMPFIRE,
        "&cBella Fire",
        "&9Enchanted flames act as",
        "&9catalysts. The flame color",
        "&9indicates temperature"
    );
}