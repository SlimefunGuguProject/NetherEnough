package me.sfiguz7.netherenough.implementation.items.machines;

import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.sfiguz7.netherenough.implementation.utils.interfaces.NEInventoryBlock;
import me.sfiguz7.netherenough.lists.NEItems;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Alembic extends SimpleSlimefunItem<BlockTicker> implements NEInventoryBlock {

    private final int[] border = {
        0, 1, 2, 6, 7, 8,
        9, 17,
        18, 26,
        31
    };
    private final int[] inputBorder = {
        3, 4, 5,
        10, 11, 12, 14, 15, 16,
        19, 21, 22, 23, 25,
        27, 28, 29, 30, 32, 33, 34, 35,
        36, 38, 42, 44,
        45, 46, 47, 51, 52, 53
    };
    private final int[] slotsBorder = {
        39, 40, 41,
        48, 50
    };
    private static final int MAX_DECREMENT = 6;
    private int decrement = MAX_DECREMENT;



    public Alembic() {
        super(NEItems.netherenough, NEItems.ALEMBIC, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {null, null, null, null, null, null, null, null, null});

        createPreset(this, this::constructMenu);
    }

    private void constructMenu(BlockMenuPreset preset) {
        for (int i : border) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), " "),
                ChestMenuUtils.getEmptyClickHandler());
        }
        for (int i : inputBorder) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), " "),
                ChestMenuUtils.getEmptyClickHandler());
        }
        for (int i : slotsBorder) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), " "),
                ChestMenuUtils.getEmptyClickHandler());
        }
    }

    // No cargo bois
    @Override
    public int[] getInputSlots() {
        return new int[] {};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] {};
    }

    private static final int[] inSlots = {13, 20, 24, 37, 43};

    private static final int OUT_SLOT = 49;

    private static final Map<ItemStack[], ItemStack> alembicRecipes = new HashMap<>();

    @Override
    public BlockTicker getItemHandler() {
        return new BlockTicker() {

            @Override
            // Fires first!! The method tick() fires after this
            public void uniqueTick() {
                // Needed to keep track of all cobble gens at once,
                // All it does is set back to max (for now 2, will be customizable)
                // when it reaches the lowest possible (AKA 1)
                if (decrement == 1) {
                    decrement = MAX_DECREMENT;
                    return;
                }
                decrement--;

            }

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {

                // We only act once per decrement cycle, when decrement got to
                // lowest and has been reset
                if (decrement != MAX_DECREMENT) {
                    return;
                }

                Block camp = b.getRelative(BlockFace.DOWN);
                Material campType = camp.getType();

                if (campType != Material.SOUL_CAMPFIRE &&
                campType != Material.CAMPFIRE) {
                    return;
                }

                BlockMenu inv = BlockStorage.getInventory(b);
                Campfire campfire = (Campfire) camp.getBlockData();
                if (campfire.isLit()) {
                    process(inv, camp, campType, campfire);
                }
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        };
    }

    private void process(BlockMenu inv, Block camp, Material campType, Campfire campfire) {
        ItemStack output = getAlembicOutput(inv);
        if (output != null) {
            for (int slot : inSlots) {
                inv.consumeItem(slot);
            }
            inv.pushItem(output.clone(), OUT_SLOT);
            if (campType == Material.SOUL_CAMPFIRE) {
                camp.setType(Material.CAMPFIRE);
            } else {
                campfire.setLit(false);
                camp.setBlockData(campfire);
            }
        }
    }

    private ItemStack getAlembicOutput(BlockMenu inv) {
        // Check if all inputs are vanilla, build input
        ItemStack[] input = {null, null, null, null, null};
        for (int i = 0; i < inSlots.length; i++) {
            ItemStack item = inv.getItemInSlot(inSlots[i]);
            if (item != null && item.hasItemMeta()) {
                input[i] = item;
            } else {
                return null;
            }
        }

        // Cycle through all recipes to find the output, defaults to null
        ItemStack output = null;
        for (ItemStack[] recipe : alembicRecipes.keySet()) {
            // Suppose it's the one
            boolean flag = true;
            for (int i = 0; i < inSlots.length; i++) {
                if (!SlimefunUtils.isItemSimilar(recipe[i], input[idx2slot(i)], true, false)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                output = alembicRecipes.get(recipe);
            }
        }
        return output;
    }

    Integer idx2slot(int i) {
        switch(i) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return 4;
        }
    }

    public static Map<ItemStack[], ItemStack> getAlembicRecipes() {
        return alembicRecipes;
    }
}


