package com.pandaismyname1.emiletsdocompat.meadow.internal;

import com.google.gson.JsonElement;
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

public class FondueFillRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final float fillAmount;

    public FondueFillRecipe(ResourceLocation id, Ingredient ingredient, float fillAmount) {
        this.id = id;
        this.ingredient = ingredient;
        this.fillAmount = fillAmount;
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
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Registry.FONDUE_FILL_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Registry.FONDUE_FILL.get();
    }

    public static class Serializer implements RecipeSerializer<FondueFillRecipe> {
        public Serializer() {

        }

        @Override
        public FondueFillRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            JsonObject ingredientObject = jsonObject.getAsJsonObject("ingredient");
            Ingredient ingredient = Ingredient.fromJson(ingredientObject);
            float fillAmount = jsonObject.get("fillAmount").getAsJsonPrimitive().getAsFloat();
            return new FondueFillRecipe(resourceLocation, ingredient, fillAmount);
        }

        @Override
        public FondueFillRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            Ingredient ingredient = Ingredient.fromNetwork(friendlyByteBuf);
            float fillAmount = friendlyByteBuf.readFloat();
            return new FondueFillRecipe(resourceLocation, ingredient,  fillAmount);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, FondueFillRecipe recipe) {
            recipe.ingredient.toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeFloat(recipe.fillAmount);
        }
    }
}