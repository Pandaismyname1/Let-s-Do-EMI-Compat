package com.pandaismyname1.emiletsdocompat.meadow.internal;

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

/**
 * A recipe representing right-click use on the Fondue block that consumes an input item,
 * outputs another item, and consumes cheese from the fondue.
 */
public class FondueUseRecipe implements Recipe<RecipeInput> {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final float cheeseCost;

    public FondueUseRecipe(Ingredient ingredient, ItemStack result, float cheeseCost) {
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

    public float getCheeseCost() {
        return cheeseCost;
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
        public @NotNull MapCodec<FondueUseRecipe> codec() {
            return RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(FondueUseRecipe::getIngredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(FondueUseRecipe::getResult),
                    com.mojang.serialization.Codec.FLOAT.fieldOf("cheeseCost").forGetter(FondueUseRecipe::getCheeseCost)
            ).apply(inst, FondueUseRecipe::new));
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, FondueUseRecipe> streamCodec() {
            return new StreamCodec<>() {
                @Override
                public void encode(RegistryFriendlyByteBuf buf, FondueUseRecipe recipe) {
                    Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.getIngredient());
                    ItemStack.STREAM_CODEC.encode(buf, recipe.getResult());
                    buf.writeFloat(recipe.getCheeseCost());
                }

                @Override
                public @NotNull FondueUseRecipe decode(RegistryFriendlyByteBuf buf) {
                    Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                    ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
                    float cheese = buf.readFloat();
                    return new FondueUseRecipe(ingredient, result, cheese);
                }
            };
        }
    }
}
