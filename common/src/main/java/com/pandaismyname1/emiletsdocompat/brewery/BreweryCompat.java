package com.pandaismyname1.emiletsdocompat.brewery;

import com.pandaismyname1.emiletsdocompat.IEmiModCompat;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.satisfy.brewery.Brewery;

public class BreweryCompat implements IEmiModCompat {

    @Override
    public void init(EmiRegistry registry, RecipeManager manager) throws NoSuchFieldException {
        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(Brewery.MOD_ID, "brewing"),
                    EmiStack.of(net.satisfy.brewery.registry.ObjectRegistry.WOODEN_BREWINGSTATION.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new BrewingRecipe(recipeCategory, (net.satisfy.brewery.recipe.BrewingRecipe) recipe));
                    },
                    net.satisfy.brewery.registry.RecipeTypeRegistry.BREWING_RECIPE_TYPE.get(),
                    EmiStack.of(net.satisfy.brewery.registry.ObjectRegistry.WOODEN_BREWINGSTATION.get()),
                    EmiStack.of(net.satisfy.brewery.registry.ObjectRegistry.COPPER_BREWINGSTATION.get()),
                    EmiStack.of(net.satisfy.brewery.registry.ObjectRegistry.NETHERITE_BREWINGSTATION.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
            // Recipe not found
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(Brewery.MOD_ID, "drying"),
                    EmiStack.of(net.satisfy.brewery.registry.ObjectRegistry.SILO_WOOD.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new DryingRecipe(recipeCategory, (net.satisfy.brewery.recipe.SiloRecipe) recipe));
                    },
                    net.satisfy.brewery.registry.RecipeTypeRegistry.SILO_RECIPE_TYPE.get(),
                    EmiStack.of(net.satisfy.brewery.registry.ObjectRegistry.SILO_WOOD.get()),
                    EmiStack.of(net.satisfy.brewery.registry.ObjectRegistry.SILO_COPPER.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
            // Recipe not found
        }
    }
}