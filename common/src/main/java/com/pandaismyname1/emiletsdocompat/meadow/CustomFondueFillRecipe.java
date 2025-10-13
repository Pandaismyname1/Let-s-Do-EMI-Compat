package com.pandaismyname1.emiletsdocompat.meadow;

import com.pandaismyname1.emiletsdocompat.MockRecipeIdGenerator;
import com.pandaismyname1.emiletsdocompat.meadow.internal.FondueFillRecipe;
import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.satisfy.meadow.core.registry.ObjectRegistry;

import java.util.function.Supplier;

public class CustomFondueFillRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TOAST_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/toast/right_click.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public CustomFondueFillRecipe(EmiRecipeCategory category, FondueFillRecipe recipe) {
        super(category, MockRecipeIdGenerator.generateRecipeId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        var input = widgets.addSlot(this.inputs.get(0), 5, 5);
        var rightClick = widgets.addTexture(TOAST_TEXTURE, 25, 3, 20, 20, 0, 0, 20, 20, 20, 20);
        var arrow = widgets.addFillingArrow(45, 5, 5000);
        var fondue = widgets.addSlot(EmiIngredient.of(Ingredient.of(ObjectRegistry.FONDUE.get())), 75, 5);
    }

    @Override
    public int getDisplayHeight() {
        return 28;
    }

    @Override
    public int getDisplayWidth() {
        return 98;
    }
}