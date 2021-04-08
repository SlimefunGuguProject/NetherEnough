package me.sfiguz7.netherenough.implementation.items.items;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.sfiguz7.netherenough.lists.NEItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AlchemyFire extends SlimefunItem {

    public AlchemyFire(AlchemyFire.Type type) {
        super(NEItems.netherenough, type.slimefunItem, type.recipeType, type.recipe);
    }

    public enum Type {
        ANTAR(RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            null, new ItemStack(Material.WITHER_ROSE), null,
            new ItemStack(Material.CRIMSON_HYPHAE), new ItemStack(Material.SOUL_CAMPFIRE), new ItemStack(Material.CRIMSON_HYPHAE),
            new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK)}, NEItems.ANTAR_FIRE
        ),
        BELLA(RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            null, new ItemStack(Material.WITHER_ROSE), null,
            new ItemStack(Material.WARPED_HYPHAE), NEItems.ANTAR_FIRE, new ItemStack(Material.WARPED_HYPHAE),
            NEItems.IGNIS, NEItems.IGNIS, NEItems.IGNIS}, NEItems.BELLA_FIRE
        );

        private final SlimefunItemStack slimefunItem;
        private final ItemStack[] recipe;
        private final RecipeType recipeType;

        Type(RecipeType recipeType, ItemStack[] recipe, SlimefunItemStack slimefunItem) {
            this.slimefunItem = slimefunItem;
            this.recipe = recipe;
            this.recipeType = recipeType;
        }
    }


}
