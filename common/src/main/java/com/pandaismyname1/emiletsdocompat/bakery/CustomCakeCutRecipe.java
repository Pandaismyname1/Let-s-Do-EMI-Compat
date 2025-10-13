package com.pandaismyname1.emiletsdocompat.bakery;

import com.pandaismyname1.emiletsdocompat.bakery.internal.CakeCutRecipe;
import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class CustomCakeCutRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TOAST_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/toasts.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public CustomCakeCutRecipe(EmiRecipeCategory category, CakeCutRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        recipe.getIngredients().forEach(ing -> this.inputs.add(EmiIngredient.of(ing)));
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(this.inputs.get(0), 5, 5);
        widgets.addTexture(TOAST_TEXTURE, 25, 3, 20, 20, 236, 20);
        widgets.addSlot(this.inputs.get(1), 45, 5);
        widgets.addFillingArrow(65, 5, 5000);
        widgets.addSlot(this.outputs.get(0), 95, 5);
    }

    @Override
    public int getDisplayHeight() { return 28; }

    @Override
    public int getDisplayWidth() { return 118; }
}
