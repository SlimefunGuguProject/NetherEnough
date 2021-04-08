package me.sfiguz7.netherenough.lists;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.sfiguz7.netherenough.NetherEnough;
import org.bukkit.NamespacedKey;

public class NERecipeType {

    static final NetherEnough instance = NetherEnough.getInstance();

    public static final RecipeType ALEMBIC = new RecipeType(new NamespacedKey(instance, "alembic"),
        NEItems.ALEMBIC
    );
}

