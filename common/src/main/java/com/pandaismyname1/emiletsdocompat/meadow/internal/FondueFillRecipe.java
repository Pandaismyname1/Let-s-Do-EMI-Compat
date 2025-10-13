package com.pandaismyname1.emiletsdocompat.meadow.internal;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class FondueFillRecipe implements Recipe<RecipeInput> {
    private final Ingredient ingredient;
    private final float fillAmount;

    public FondueFillRecipe(Ingredient ingredient, float fillAmount) {
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
    public boolean matches(RecipeInput recipeInput, Level level) {
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Registry.FONDUE_FILL_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Registry.FONDUE_FILL.get();
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public float getFillAmount() {
        return fillAmount;
    }

    public static class Serializer implements RecipeSerializer<FondueFillRecipe> {
        @Override
        public @NotNull MapCodec<FondueFillRecipe> codec() {
            return RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(FondueFillRecipe::getIngredient),
                    com.mojang.serialization.Codec.FLOAT.fieldOf("fillAmount").forGetter(FondueFillRecipe::getFillAmount)
            ).apply(inst, FondueFillRecipe::new));
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, FondueFillRecipe> streamCodec() {
            return new StreamCodec<>() {
                @Override
                public void encode(RegistryFriendlyByteBuf buf, FondueFillRecipe recipe) {
                    Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.getIngredient());
                    buf.writeFloat(recipe.getFillAmount());
                }

                @Override
                public @NotNull FondueFillRecipe decode(RegistryFriendlyByteBuf buf) {
                    Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                    float fill = buf.readFloat();
                    return new FondueFillRecipe(ingredient, fill);
                }
            };
        }
    }
}