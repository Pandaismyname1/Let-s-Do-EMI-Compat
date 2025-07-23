package com.pandaismyname1.emiletsdocompat.meadow;

import com.pandaismyname1.emiletsdocompat.IEmiModCompat;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.satisfy.meadow.Meadow;
import net.satisfy.meadow.core.registry.ObjectRegistry;

public class MeadowCompat implements IEmiModCompat {
    @Override
    public void init(EmiRegistry registry, RecipeManager manager) throws NoSuchFieldException {

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(Meadow.MOD_ID, "cheese"),
                    EmiStack.of(ObjectRegistry.CHEESE_FORM.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new CheeseFormRecipe(recipeCategory, (net.satisfy.meadow.core.recipes.CheeseFormRecipe) recipe));
                    },
                    net.satisfy.meadow.core.registry.RecipeRegistry.CHEESE.get(),
                    EmiStack.of(ObjectRegistry.CHEESE_FORM.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(Meadow.MOD_ID, "cooking"),
                    EmiStack.of(ObjectRegistry.COOKING_CAULDRON.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new CookingCauldronRecipe(recipeCategory, (net.satisfy.meadow.core.recipes.CookingCauldronRecipe) recipe));
                    },
                    net.satisfy.meadow.core.registry.RecipeRegistry.COOKING.get(),
                    EmiStack.of(ObjectRegistry.COOKING_CAULDRON.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(Meadow.MOD_ID, "woodcutting"),
                    EmiStack.of(ObjectRegistry.WOODCUTTER.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new WoodcuttingRecipe(recipeCategory, (net.satisfy.meadow.core.recipes.WoodcuttingRecipe) recipe));
                    },
                    net.satisfy.meadow.core.registry.RecipeRegistry.WOODCUTTING.get(),
                    EmiStack.of(ObjectRegistry.WOODCUTTER.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }
    }
}
