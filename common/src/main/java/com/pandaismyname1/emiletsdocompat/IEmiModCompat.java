package com.pandaismyname1.emiletsdocompat;

import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.crafting.*;

import java.util.function.Consumer;

public interface IEmiModCompat {
    void init(EmiRegistry registry, RecipeManager manager) throws NoSuchFieldException;


    default <I extends RecipeInput, T extends Recipe<I>, M extends EmiRecipe> void registerRecipeType(EmiRegistry registry, RecipeManager manager, EmiRecipeCategory category, Consumer<T> onRecipe, RecipeType recipeType, EmiStack... blocks) throws NoSuchFieldException, NoSuchFieldError, ClassNotFoundException {
        registry.addCategory(category);
        for (EmiStack block : blocks) {
            registry.addWorkstation(category, block);
        }

        for (var recipe : manager.getAllRecipesFor(recipeType)) {
            var holder = (RecipeHolder<T>) recipe;
            onRecipe.accept(holder.value());
        }
    }
}
