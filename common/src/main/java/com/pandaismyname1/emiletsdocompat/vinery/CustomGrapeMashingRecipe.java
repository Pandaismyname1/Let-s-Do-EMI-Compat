package com.pandaismyname1.emiletsdocompat.vinery;

import com.pandaismyname1.emiletsdocompat.Emi_letsdo_compat;
import com.pandaismyname1.emiletsdocompat.vinery.internal.GrapeMashingRecipe;
import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.vinery.core.registry.ObjectRegistry;

import java.util.List;
import java.util.function.Supplier;


public class CustomGrapeMashingRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TOAST_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/toasts.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());
    private static float time = 0;

    public CustomGrapeMashingRecipe(EmiRecipeCategory category, GrapeMashingRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        recipe.getIngredients().forEach(ing -> this.inputs.add(EmiIngredient.of(ing)));
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(this.inputs.get(0), 5, 5);
        widgets.addTexture(TOAST_TEXTURE, 25, 3, 20, 20, 236, 20);
        widgets.addSlot(EmiStack.of(ObjectRegistry.GRAPEVINE_POT.get()), 45, 5);
        widgets.addFillingArrow(65, 5, 5000);
        widgets.addSlot(this.outputs.get(0), 95, 5);
        widgets.addTooltipText(List.of(Component.translatable("emi_letsdo_compat.jump_on_pot")), 65, 5, 28, 20);
    }

    @Override
    public int getDisplayHeight() { return 28; }

    @Override
    public int getDisplayWidth() { return 118; }
}
