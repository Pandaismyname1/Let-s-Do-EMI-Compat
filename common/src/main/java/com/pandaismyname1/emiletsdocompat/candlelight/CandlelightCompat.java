package com.pandaismyname1.emiletsdocompat.candlelight;

import com.pandaismyname1.emiletsdocompat.IEmiModCompat;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.satisfy.candlelight.Candlelight;
import net.satisfy.candlelight.registry.ObjectRegistry;

public class CandlelightCompat implements IEmiModCompat {
    @Override
    public void init(EmiRegistry registry, RecipeManager manager) throws NoSuchFieldException {
        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(Candlelight.MOD_ID, "pan_cooking"),
                    EmiStack.of(ObjectRegistry.COOKING_PAN.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new PanCookingRecipe(recipeCategory, (net.satisfy.candlelight.recipe.CookingPanRecipe) recipe));
                    },
                    net.satisfy.candlelight.registry.RecipeTypeRegistry.COOKING_PAN_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.COOKING_PAN.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(Candlelight.MOD_ID, "pot_cooking"),
                    EmiStack.of(ObjectRegistry.COOKING_POT.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new PotCookingRecipe(recipeCategory, (net.satisfy.candlelight.recipe.CookingPotRecipe) recipe));
                    },
                    net.satisfy.candlelight.registry.RecipeTypeRegistry.COOKING_POT_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.COOKING_POT.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }
    }
}
