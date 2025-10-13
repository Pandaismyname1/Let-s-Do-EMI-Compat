package com.pandaismyname1.emiletsdocompat.vinery.internal;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class GrapeMashingRecipe implements Recipe<RecipeInput> {
    private final Ingredient ingredient;
    private final ItemStack result;

    public GrapeMashingRecipe(Ingredient ingredient, ItemStack result) {
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.ingredient);
        return list;
    }

    @Override
    public boolean matches(RecipeInput recipeInput, Level level) {
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result.copy();
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public ItemStack getResult() {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Registry.GRAPE_MASHING_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Registry.GRAPE_MASHING.get();
    }

    public static class Serializer implements RecipeSerializer<GrapeMashingRecipe> {
        @Override
        public @NotNull MapCodec<GrapeMashingRecipe> codec() {
            return RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(GrapeMashingRecipe::getIngredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(GrapeMashingRecipe::getResult)
            ).apply(inst, GrapeMashingRecipe::new));
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, GrapeMashingRecipe> streamCodec() {
            return new StreamCodec<>() {
                @Override
                public void encode(RegistryFriendlyByteBuf buf, GrapeMashingRecipe recipe) {
                    Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.getIngredient());
                    ItemStack.STREAM_CODEC.encode(buf, recipe.getResult());
                }

                @Override
                public @NotNull GrapeMashingRecipe decode(RegistryFriendlyByteBuf buf) {
                    return new GrapeMashingRecipe(Ingredient.CONTENTS_STREAM_CODEC.decode(buf), ItemStack.STREAM_CODEC.decode(buf));
                }
            };
        }
    }
}
