package com.pandaismyname1.emiletsdocompat.bakery;

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

public class BakingStationRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = new ResourceLocation("bakery", "textures/gui/baking_station.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public BakingStationRecipe(EmiRecipeCategory category, net.satisfy.bakery.recipe.BakingStationRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(TEXTURE, 0, 0, 124, 60, 26, 12);
        widgets.addFillingArrow(52, 24, 5000);


        if (!this.inputs.isEmpty()) {
            var s = widgets.addSlot(this.inputs.get(0), 49 - 26, 24 - 12);
            s.drawBack(false);
        }

        if (this.inputs.size() > 1) {
            var s = widgets.addSlot(this.inputs.get(1), 49 - 26, 42 - 12);
            s.drawBack(false);
        }

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 109 - 26, 34 - 12);
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