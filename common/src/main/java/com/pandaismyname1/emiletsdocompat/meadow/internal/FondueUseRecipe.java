package com.pandaismyname1.emiletsdocompat.meadow.internal;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * A recipe representing right-click use on the Fondue block that consumes an input item,
 * outputs another item, and consumes cheese from the fondue.
 */
public class FondueUseRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final ItemStack result;
    private final float cheeseCost;

    public FondueUseRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, float cheeseCost) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.cheeseCost = cheeseCost;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.ingredient);
        return list;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    public float getCheeseCost() {
        return cheeseCost;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Registry.FONDUE_USE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Registry.FONDUE_USE.get();
    }

    public static class Serializer implements RecipeSerializer<FondueUseRecipe> {
        @Override
        public FondueUseRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(json.getAsJsonObject("ingredient"));
            JsonObject resultObj = json.getAsJsonObject("result");
            ItemStack result = net.minecraft.world.item.crafting.ShapedRecipe.itemStackFromJson(resultObj);
            float cheeseCost = json.getAsJsonPrimitive("cheeseCost").getAsFloat();
            return new FondueUseRecipe(id, ingredient, result, cheeseCost);
        }

        @Override
        public FondueUseRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            float cheeseCost = buf.readFloat();
            return new FondueUseRecipe(id, ingredient, result, cheeseCost);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FondueUseRecipe recipe) {
            recipe.ingredient.toNetwork(buf);
            buf.writeItem(recipe.result);
            buf.writeFloat(recipe.cheeseCost);
        }
    }
}
