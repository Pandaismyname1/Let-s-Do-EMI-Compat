package com.pandaismyname1.emiletsdocompat.bakery.internal;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CakeCutRecipe implements Recipe<Container> {
    private static final TagKey<Item> KNIFE_TAG = TagKey.create(Registries.ITEM, new ResourceLocation("bakery", "knives"));
    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final ItemStack result;

    public CakeCutRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(Ingredient.of(KNIFE_TAG));
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

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Registry.CAKE_CUT_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Registry.CAKE_CUT.get();
    }

    public static class Serializer implements RecipeSerializer<CakeCutRecipe> {
        @Override
        public CakeCutRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(json.getAsJsonObject("ingredient"));
            ItemStack result = net.minecraft.world.item.crafting.ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
            return new CakeCutRecipe(id, ingredient, result);
        }

        @Override
        public CakeCutRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            return new CakeCutRecipe(id, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CakeCutRecipe recipe) {
            recipe.ingredient.toNetwork(buf);
            buf.writeItem(recipe.result);
        }
    }
}
