package com.pandaismyname1.emiletsdocompat.beachparty;

import com.pandaismyname1.emiletsdocompat.MockRecipeIdGenerator;
import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.RegistryAccess;
import net.satisfy.beachparty.client.gui.MiniFridgeGui;
import net.satisfy.beachparty.client.gui.PalmBarGui;
import net.satisfy.beachparty.core.recipe.MiniFridgeRecipe;

import java.util.function.Supplier;

public class MiniFridgeMixingRecipe extends BasicEmiRecipe {
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public MiniFridgeMixingRecipe(EmiRecipeCategory category, MiniFridgeRecipe recipe) {
        super(category, MockRecipeIdGenerator.generateRecipeId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(MiniFridgeGui.BG, 0, 0, 88, 34, 52, 26);
        widgets.addAnimatedTexture(MiniFridgeGui.BG, 27, 8, 24, 17, 176, 14, 5000, true, false, false);

        if (!this.inputs.isEmpty()) {
            var s = widgets.addSlot(this.inputs.get(0), 3, 8);
            s.drawBack(false);
        }

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 63, 8);
            s.drawBack(false);
        }
    }

    @Override
    public int getDisplayHeight() {
        return 34;
    }

    @Override
    public int getDisplayWidth() {
        return 88;
    }
}