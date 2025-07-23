package com.pandaismyname1.emiletsdocompat.beachparty;

import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.RegistryAccess;
import satisfy.beachparty.client.gui.TikiBarGui;

import java.util.function.Supplier;

public class TikiBarMixingRecipe extends BasicEmiRecipe {
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public TikiBarMixingRecipe(EmiRecipeCategory category, satisfy.beachparty.recipe.TikiBarRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(TikiBarGui.BG, -1, -1, 124, 60, 26, 13);
        widgets.addAnimatedTexture(TikiBarGui.BG, 67, 31, 22, 10, 177, 26, 5000, true, false, false);
        widgets.addAnimatedTexture(TikiBarGui.BG, 69, 8, 15, 20, 179, 2, 5000, false, true, false);

        if (!this.inputs.isEmpty()) {
            var s = widgets.addSlot(this.inputs.get(0), 27, 11);
            s.drawBack(false);
        }

        if (this.inputs.size() > 1) {
            var s = widgets.addSlot(this.inputs.get(1), 27, 29);
            s.drawBack(false);
        }

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 100, 20);
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