package com.pandaismyname1.emiletsdocompat.bakery_fc;

import com.pandaismyname1.emiletsdocompat.IEmiModCompat;
import com.pandaismyname1.emiletsdocompat.bakery.CustomCakeCutRecipe;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

public class BakeryFCCompat implements IEmiModCompat {

    public void init(EmiRegistry registry, RecipeManager manager) throws NoSuchFieldException {
        try {
            var recipeCategory = new EmiRecipeCategory(ResourceLocation.fromNamespaceAndPath(net.satisfy.bakery.Bakery.MOD_ID, "caking"),
                    EmiStack.of(net.satisfy.bakery.core.registry.ObjectRegistry.BAKER_STATION.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new BakingStationRecipe(recipeCategory, (net.satisfy.bakery.core.recipe.BakingStationRecipe) recipe));
                    },
                    net.satisfy.bakery.core.registry.RecipeTypeRegistry.BAKING_STATION_RECIPE_TYPE.get(),
                    EmiStack.of(net.satisfy.bakery.core.registry.ObjectRegistry.BAKER_STATION.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }
        try {
            var recipeCategory = new EmiRecipeCategory(ResourceLocation.fromNamespaceAndPath(com.pandaismyname1.emiletsdocompat.Emi_letsdo_compat.MOD_ID, "cake_cut"),
                    EmiStack.of(net.minecraft.world.item.Items.CAKE));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new CustomCakeCutRecipe(recipeCategory, (com.pandaismyname1.emiletsdocompat.bakery.internal.CakeCutRecipe) recipe));
                    },
                    com.pandaismyname1.emiletsdocompat.bakery.internal.Registry.CAKE_CUT.get());
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }
    }
}
