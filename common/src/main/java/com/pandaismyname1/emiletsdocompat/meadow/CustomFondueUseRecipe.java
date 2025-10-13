package com.pandaismyname1.emiletsdocompat.meadow;

import com.pandaismyname1.emiletsdocompat.MockRecipeIdGenerator;
import com.pandaismyname1.emiletsdocompat.meadow.internal.FondueUseRecipe;
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

public class CustomFondueUseRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TOAST_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/toasts.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public CustomFondueUseRecipe(EmiRecipeCategory category, FondueUseRecipe recipe) {
        super(category, MockRecipeIdGenerator.generateRecipeId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(this.inputs.get(0), 5, 5);
        widgets.addTexture(TOAST_TEXTURE, 25, 3, 20, 20, 236, 20);
        widgets.addSlot(EmiIngredient.of(Ingredient.of(ObjectRegistry.FONDUE.get())), 45, 5);
        widgets.addFillingArrow(65, 5, 5000);
        widgets.addSlot(this.outputs.get(0), 95, 5);
    }

    @Override
    public int getDisplayHeight() {
        return 28;
    }

    @Override
    public int getDisplayWidth() {
        return 118;
    }
}
