package com.pandaismyname1.emiletsdocompat.bakery;

import com.pandaismyname1.emiletsdocompat.IEmiModCompat;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

public class BakeryCompat implements IEmiModCompat {

    public void init(EmiRegistry registry, RecipeManager manager) throws NoSuchFieldException {
//        try {
//            var recipeCategory = new EmiRecipeCategory(ResourceLocation.fromNamespaceAndPath(net.satisfy.bakery.Bakery.MOD_ID, "caking"),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.BAKER_STATION.get()));
//            registerRecipeType(registry, manager,
//                    recipeCategory,
//                    (recipe) -> {
//                        registry.addRecipe(new BakingStationRecipe(recipeCategory, (net.satisfy.bakery.recipe.BakingStationRecipe) recipe));
//                    },
//                    net.satisfy.bakery.registry.RecipeTypeRegistry.BAKING_STATION_RECIPE_TYPE.get(),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.BAKER_STATION.get()));
//        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
//        }
//
//        try {
//            var recipeCategory = new EmiRecipeCategory(ResourceLocation.fromNamespaceAndPath(net.satisfy.bakery.Bakery.MOD_ID, "doughing"),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.CRAFTING_BOWL.get()));
//            registerRecipeType(registry, manager,
//                    recipeCategory,
//                    (recipe) -> {
//                        registry.addRecipe(new CraftingBowlRecipe(recipeCategory, (net.satisfy.bakery.recipe.CraftingBowlRecipe) recipe));
//                    },
//                    net.satisfy.bakery.registry.RecipeTypeRegistry.CRAFTING_BOWL_RECIPE_TYPE.get(),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.CRAFTING_BOWL.get()));
//        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
//        }
//
//        try {
//            var recipeCategory = new EmiRecipeCategory(ResourceLocation.fromNamespaceAndPath(net.satisfy.bakery.Bakery.MOD_ID, "stove"),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.BRICK_STOVE.get()));
//            registerRecipeType(registry, manager,
//                    recipeCategory,
//                    (recipe) -> {
//                        registry.addRecipe(new StoveRecipe(recipeCategory, (net.satisfy.bakery.recipe.StoveRecipe) recipe));
//                    },
//                    net.satisfy.bakery.registry.RecipeTypeRegistry.STOVE_RECIPE_TYPE.get(),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.BRICK_STOVE.get()),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.COBBLESTONE_STOVE.get()),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.SANDSTONE_STOVE.get()),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.STONE_BRICKS_STOVE.get()),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.DEEPSLATE_STOVE.get()),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.GRANITE_STOVE.get()),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.END_STOVE.get()),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.MUD_STOVE.get()),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.QUARTZ_STOVE.get()),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.RED_NETHER_BRICKS_STOVE.get()));
//        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
//        }
//
//        try {
//            var recipeCategory = new EmiRecipeCategory(ResourceLocation.fromNamespaceAndPath(net.satisfy.bakery.Bakery.MOD_ID, "pot_cooking"),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.SMALL_COOKING_POT.get()));
//            registerRecipeType(registry, manager,
//                    recipeCategory,
//                    (recipe) -> {
//                        registry.addRecipe(new PotCookingRecipe(recipeCategory, (net.satisfy.bakery.recipe.CookingPotRecipe) recipe));
//                    },
//                    net.satisfy.bakery.registry.RecipeTypeRegistry.COOKING_POT_RECIPE_TYPE.get(),
//                    EmiStack.of(net.satisfy.bakery.registry.ObjectRegistry.SMALL_COOKING_POT.get()));
//        } catch (NoSuchFieldError | NoSuchFieldException | ClassNotFoundException e) {
//        }
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
