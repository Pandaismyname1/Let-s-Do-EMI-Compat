package com.pandaismyname1.emiletsdocompat.bakery.internal;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CakeCutRecipe implements Recipe<RecipeInput> {
    private static final TagKey<Item> KNIFE_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("bakery", "knives"));
    private final Ingredient ingredient;
    private final ItemStack result;

    public CakeCutRecipe(Ingredient ingredient, ItemStack result) {
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(Ingredient.of(KNIFE_TAG));
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

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Registry.CAKE_CUT_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Registry.CAKE_CUT.get();
    }


    public Ingredient getInput() {
        return this.ingredient;
    }

    public ItemStack getOutput() {
        return this.result;
    }

    public static class Serializer implements RecipeSerializer<CakeCutRecipe> {
        @Override
        public @NotNull MapCodec<CakeCutRecipe> codec() {
            return RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(CakeCutRecipe::getInput),
                    ItemStack.CODEC.fieldOf("output").forGetter(CakeCutRecipe::getOutput)
            ).apply(inst, CakeCutRecipe::new));
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, CakeCutRecipe> streamCodec() {
            return new StreamCodec<>(){

                @Override
                public void encode(RegistryFriendlyByteBuf buf, CakeCutRecipe recipe) {
                    Ingredient.CONTENTS_STREAM_CODEC.encode(buf,recipe.getInput());
                    ItemStack.STREAM_CODEC.encode(buf,recipe.getOutput());
                }

                @Override
                public @NotNull CakeCutRecipe decode(RegistryFriendlyByteBuf buf) {
                    return new CakeCutRecipe(Ingredient.CONTENTS_STREAM_CODEC.decode(buf),ItemStack.STREAM_CODEC.decode(buf));                }
            };
        }
    }
}
