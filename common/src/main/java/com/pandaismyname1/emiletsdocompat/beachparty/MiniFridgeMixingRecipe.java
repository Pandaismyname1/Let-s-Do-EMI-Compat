package com.pandaismyname1.emiletsdocompat.beachparty;

import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.RegistryAccess;
import satisfy.beachparty.client.gui.MiniFridgeGui;

import java.util.function.Supplier;

public class MiniFridgeMixingRecipe extends BasicEmiRecipe {
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public MiniFridgeMixingRecipe(EmiRecipeCategory category, satisfy.beachparty.recipe.MiniFridgeRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(MiniFridgeGui.BG, -1, -1, 124, 60, 26, 13);
        widgets.addAnimatedTexture(MiniFridgeGui.BG, 67, 31, 22, 10, 177, 26, 5000, true, false, false);

        if (!this.inputs.isEmpty()) {
            var s = widgets.addSlot(this.inputs.get(0), 18, 12);
            s.drawBack(false);
        }

        if (this.inputs.size() > 1) {
            var s = widgets.addSlot(this.inputs.get(1), 31, 28);
            s.drawBack(false);
        }

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 100, 27);
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