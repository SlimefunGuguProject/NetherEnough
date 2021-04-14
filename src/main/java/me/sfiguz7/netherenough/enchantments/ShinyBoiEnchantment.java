package me.sfiguz7.netherenough.enchantments;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.sfiguz7.netherenough.lists.NEItems;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class ShinyBoiEnchantment extends Enchantment {

    public ShinyBoiEnchantment(NamespacedKey key) {
        super(key);
    }

    @Nonnull
    @Override
    @Deprecated
    public String getName() {
        return "Shiny Boi";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    @Deprecated
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return SlimefunUtils.isItemSimilar(itemStack, NEItems.IGNIS, false) ||
            SlimefunUtils.isItemSimilar(itemStack, NEItems.AQUA, false) ||
            SlimefunUtils.isItemSimilar(itemStack, NEItems.TERRA, false) ||
            SlimefunUtils.isItemSimilar(itemStack, NEItems.FULGUR, false) ||
            SlimefunUtils.isItemSimilar(itemStack, NEItems.AURA, false) ||
            SlimefunUtils.isItemSimilar(itemStack, NEItems.ARCANE_POWDER, false);
    }
}
