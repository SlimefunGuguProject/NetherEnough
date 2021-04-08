package me.sfiguz7.netherenough.implementation.items.items;

import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.sfiguz7.netherenough.implementation.items.machines.Alembic;
import me.sfiguz7.netherenough.lists.Constants;
import me.sfiguz7.netherenough.lists.NEItems;
import me.sfiguz7.netherenough.lists.NERecipeType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Powder extends SlimefunItem implements NotPlaceable {

    public Powder(Type type) {
        super(NEItems.netherenough, type.slimefunItem, NERecipeType.ALEMBIC, type.recipe);
    }

    public enum Type {
        FIRE(new ItemStack[] {new ItemStack(Material.REDSTONE), new ItemStack(Material.LAVA_BUCKET), new ItemStack(Material.TNT), new ItemStack(Material.RED_NETHER_BRICKS), new ItemStack(Material.BLAZE_POWDER)}, NEItems.IGNIS
        ),
        WATER(new ItemStack[] {new ItemStack(Material.LAPIS_LAZULI), new ItemStack(Material.WARPED_FUNGUS), new ItemStack(Material.BLUE_GLAZED_TERRACOTTA), new ItemStack(Material.BLUE_WOOL), new ItemStack(Material.PACKED_ICE)}, NEItems.AQUA
        ),
        EARTH(new ItemStack[] {new ItemStack(Material.EMERALD), new ItemStack(Material.ZOMBIE_HEAD), new ItemStack(Material.SLIME_BLOCK), new ItemStack(Material.SCUTE), new ItemStack(Material.LILY_PAD)}, NEItems.TERRA
        ),
        LIGHTNING(new ItemStack[] {new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.END_STONE_BRICKS), new ItemStack(Material.GLOWSTONE), new ItemStack(Material.SPONGE), new ItemStack(Material.SUNFLOWER)}, NEItems.FULGUR
        ),
        AIR(new ItemStack[] {new ItemStack(Material.IRON_INGOT), new ItemStack(Material.MILK_BUCKET), new ItemStack(Material.QUARTZ_BLOCK), new ItemStack(Material.FEATHER), new ItemStack(Material.GHAST_TEAR)}, NEItems.AURA
        ),
        ARCANE(new ItemStack[] {NEItems.IGNIS, NEItems.AQUA, NEItems.TERRA, NEItems.FULGUR, NEItems.AURA}, NEItems.ARCANE_POWDER
        );

        private final SlimefunItemStack slimefunItem;
        private final ItemStack[] recipe;

        Type(ItemStack[] inputs, SlimefunItemStack slimefunItem) {
            this.slimefunItem = slimefunItem;
            this.recipe = new ItemStack[]{
                inputs[0], inputs[1], inputs[2],
                inputs[3], null, inputs[4],
                null, null, null
            };

            Alembic.getAlembicRecipes().put(inputs, slimefunItem);
        }
    }

    @Override
    public void preRegister() {
        Enchantment glow = Enchantment.getByKey(Constants.SHINY_BOI_ENCHANTMENT);
        NEItems.IGNIS.addUnsafeEnchantment(glow, 1);
        NEItems.AQUA.addUnsafeEnchantment(glow, 1);
        NEItems.TERRA.addUnsafeEnchantment(glow, 1);
        NEItems.FULGUR.addUnsafeEnchantment(glow, 1);
        NEItems.AURA.addUnsafeEnchantment(glow, 1);
        NEItems.ARCANE_POWDER.addUnsafeEnchantment(glow, 1);
    }
}


