package com.pandaismyname1.emiletsdocompat.herbalbrews;

import com.pandaismyname1.emiletsdocompat.MockRecipeIdGenerator;
import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;
import net.satisfy.herbalbrews.core.registry.TagsRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class TeaKettleBrewingRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("herbalbrews", "textures/gui/tea_kettle.png");
    private static final EmiIngredient WATER_INGREDIENT;
    private static final EmiIngredient HEAT_INGREDIENT;
    private static final EmiIngredient BOTTLE_INGREDIENT;

    static {
        WATER_INGREDIENT = EmiIngredient.of(
                List.of(
                        EmiIngredient.of(Ingredient.of(TagsRegistry.SMALL_WATER_FILL)),
                        EmiIngredient.of(Ingredient.of(TagsRegistry.LARGE_WATER_FILL))
                )
        );
        HEAT_INGREDIENT = EmiIngredient.of(Ingredient.of(TagsRegistry.HEAT_ITEMS));
        BOTTLE_INGREDIENT = EmiIngredient.of(Ingredient.of(TagsRegistry.CONTAINER_ITEMS));
    }
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public TeaKettleBrewingRecipe(EmiRecipeCategory category, net.satisfy.herbalbrews.core.recipe.TeaKettleRecipe recipe) {
        super(category, MockRecipeIdGenerator.generateRecipeId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            if (Arrays.stream(ingredient.getItems()).noneMatch((stack) -> stack.is(TagsRegistry.CONTAINER_ITEMS))) {
                this.inputs.add(EmiIngredient.of(ingredient));
            }
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        var bgTex = widgets.addTexture(TEXTURE, 0, 0, 157, 69, 10, 9);
        var waterTex = widgets.addAnimatedTexture(TEXTURE, 131, 7, 8, 43, 183, 31, 5000, false, true, true);
        var heatTex = widgets.addAnimatedTexture(TEXTURE, 146, 7, 5, 43, 176, 31, 5000, false, true, true);
        var fireTex = widgets.addAnimatedTexture(TEXTURE, 141, 53, 14, 14, 176, 0, 5000, false, true, false);
        var arrowTex = widgets.addAnimatedTexture(TEXTURE, 44, 12, 24, 17, 176, 14, 5000, true, false, false);


        var input1 = widgets.addSlot(this.inputs.get(0), 2, 2);
        input1.drawBack(false);

        if (this.inputs.size() > 1) {
            var input2 = widgets.addSlot(this.inputs.get(1), 20, 2);
            input2.drawBack(false);
        }

        if (this.inputs.size() > 2) {
            var input3 = widgets.addSlot(this.inputs.get(2), 2, 20);
            input3.drawBack(false);
        }

        if (this.inputs.size() > 3) {
            var input4 = widgets.addSlot(this.inputs.get(3), 20, 20);
            input4.drawBack(false);
        }

        var bottle =  widgets.addSlot(BOTTLE_INGREDIENT, 20, 42);
        bottle.drawBack(false);

        var water = widgets.addSlot(WATER_INGREDIENT, 107, 33);
        water.drawBack(false);

        var  heat = widgets.addSlot(HEAT_INGREDIENT, 84, 48);
        heat.drawBack(false);

        var output = widgets.addSlot(this.outputs.get(0), 80, 12);
        output.drawBack(false);
    }

    @Override
    public int getDisplayHeight() {
        return 69;
    }

    @Override
    public int getDisplayWidth() {
        return 157;
    }
}