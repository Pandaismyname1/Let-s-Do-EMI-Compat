package com.pandaismyname1.emiletsdocompat.beachparty;

import com.pandaismyname1.emiletsdocompat.IEmiModCompat;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.satisfy.beachparty.Beachparty;
import net.satisfy.beachparty.core.registry.ObjectRegistry;
import net.satisfy.beachparty.core.registry.RecipeRegistry;

public class BeachpartyCompat implements IEmiModCompat {

    @Override
    public void init(EmiRegistry registry, RecipeManager manager) throws NoSuchFieldException {

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(Beachparty.MOD_ID, "mini_fridge_mixing"),
                    EmiStack.of(ObjectRegistry.MINI_FRIDGE.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new MiniFridgeMixingRecipe(recipeCategory, (net.satisfy.beachparty.core.recipe.MiniFridgeRecipe) recipe));
                    },
                    RecipeRegistry.MINI_FRIDGE_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.MINI_FRIDGE.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(Beachparty.MOD_ID, "tiki_bar_mixing"),
                    EmiStack.of(ObjectRegistry.PALM_BAR.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new TikiBarMixingRecipe(recipeCategory, (net.satisfy.beachparty.core.recipe.PalmBarRecipe) recipe));
                    },
                    RecipeRegistry.PALM_BAR_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.PALM_BAR.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }
    }
}
