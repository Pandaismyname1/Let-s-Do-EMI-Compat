package com.pandaismyname1.emiletsdocompat.bakery.internal;

import com.pandaismyname1.emiletsdocompat.Emi_letsdo_compat;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class Registry {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Emi_letsdo_compat.MOD_ID, Registries.RECIPE_SERIALIZER);
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Emi_letsdo_compat.MOD_ID, Registries.RECIPE_TYPE);

    public static final RegistrySupplier<RecipeType<CakeCutRecipe>> CAKE_CUT = create("cake_cut");
    public static final RegistrySupplier<RecipeSerializer<CakeCutRecipe>> CAKE_CUT_SERIALIZER = create("cake_cut", CakeCutRecipe.Serializer::new);

    private static <T extends Recipe<?>> RegistrySupplier<RecipeSerializer<T>> create(String name, Supplier<RecipeSerializer<T>> serializer) {
        return RECIPE_SERIALIZERS.register(name, serializer);
    }

    private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> create(String name) {
        Supplier<RecipeType<T>> type = () -> new RecipeType<T>() {
            public String toString() { return name; }
        };
        return RECIPE_TYPES.register(name, type);
    }

    public static void init() {
        RECIPE_SERIALIZERS.register();
        RECIPE_TYPES.register();
    }
}
