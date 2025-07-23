package com.pandaismyname1.emiletsdocompat.vinery;

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

public class ApplePressingRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = new ResourceLocation("vinery", "textures/gui/apple_press_gui.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public ApplePressingRecipe(EmiRecipeCategory category, net.satisfy.vinery.core.recipe.ApplePressMashingRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(TEXTURE, 13, 3, 28, 54, 38, 15);
        widgets.addAnimatedTexture(TEXTURE, 15, 5, 25, 38, 176, 0, 5000, false, false, false);
        widgets.addFillingArrow(48, 25, 5000);
        widgets.addTexture(TEXTURE, 80, 25, 36, 18, 100, 49);


        if (!this.inputs.isEmpty()) {
            var s = widgets.addSlot(this.inputs.get(0), 18, 21);
            s.drawBack(false);
        }

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 80, 25);
            s.drawBack(false);
        }
    }

    @Override
    public int getDisplayHeight() {
        return 60;
    }

    @Override
    public int getDisplayWidth() {
        return 124;
    }
}