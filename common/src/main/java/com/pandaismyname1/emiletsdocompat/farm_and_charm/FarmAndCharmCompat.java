package com.pandaismyname1.emiletsdocompat.farm_and_charm;

import com.pandaismyname1.emiletsdocompat.IEmiModCompat;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.satisfy.farm_and_charm.FarmAndCharm;
import net.satisfy.farm_and_charm.core.registry.ObjectRegistry;

public class FarmAndCharmCompat implements IEmiModCompat {
    @Override
    public void init(EmiRegistry registry, RecipeManager manager) throws NoSuchFieldException {

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(FarmAndCharm.MOD_ID, "pot_cooking"),
                    EmiStack.of(ObjectRegistry.COOKING_POT.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new PotCookingRecipe(recipeCategory, (net.satisfy.farm_and_charm.core.recipe.CookingPotRecipe) recipe));
                    },
                    net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry.COOKING_POT_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.COOKING_POT.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(FarmAndCharm.MOD_ID, "crafting_bowl"),
                    EmiStack.of(ObjectRegistry.CRAFTING_BOWL.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new CraftingBowlRecipe(recipeCategory, (net.satisfy.farm_and_charm.core.recipe.CraftingBowlRecipe) recipe));
                    },
                    net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry.CRAFTING_BOWL_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.CRAFTING_BOWL.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(FarmAndCharm.MOD_ID, "mincer"),
                    EmiStack.of(ObjectRegistry.MINCER.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new MincerRecipe(recipeCategory, (net.satisfy.farm_and_charm.core.recipe.MincerRecipe) recipe));
                    },
                    net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry.MINCER_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.MINCER.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(FarmAndCharm.MOD_ID, "roaster"),
                    EmiStack.of(ObjectRegistry.ROASTER.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new RoasterRecipe(recipeCategory, (net.satisfy.farm_and_charm.core.recipe.RoasterRecipe) recipe));
                    },
                    net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry.ROASTER_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.ROASTER.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(FarmAndCharm.MOD_ID, "drying"),
                    EmiStack.of(ObjectRegistry.SILO_WOOD.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new SiloRecipe(recipeCategory, (net.satisfy.farm_and_charm.core.recipe.SiloRecipe) recipe));
                    },
                    net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry.SILO_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.SILO_WOOD.get()),
                    EmiStack.of(ObjectRegistry.SILO_COPPER.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }

        try {
            var recipeCategory = new EmiRecipeCategory(new ResourceLocation(FarmAndCharm.MOD_ID, "stove"),
                    EmiStack.of(ObjectRegistry.STOVE.get()));
            registerRecipeType(registry, manager,
                    recipeCategory,
                    (recipe) -> {
                        registry.addRecipe(new StoveRecipe(recipeCategory, (net.satisfy.farm_and_charm.core.recipe.StoveRecipe) recipe));
                    },
                    net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry.STOVE_RECIPE_TYPE.get(),
                    EmiStack.of(ObjectRegistry.STOVE.get()));
        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
        }
    }
}
