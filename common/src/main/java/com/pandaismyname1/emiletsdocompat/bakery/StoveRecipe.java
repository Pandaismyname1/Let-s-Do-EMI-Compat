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

public class StoveRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = new ResourceLocation("bakery", "textures/gui/stove_gui.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public StoveRecipe(EmiRecipeCategory category, net.satisfy.bakery.recipe.StoveRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(TEXTURE, 0, 0, 124, 60, 25, 13);
        widgets.addAnimatedTexture(TEXTURE, 66, 15, 19, 29, 176, 16, 5000, true, false, false);
        widgets.addAnimatedTexture(TEXTURE, 37, 36, 17, 14, 176, 0, 5000, false, true, false);


        if (!this.inputs.isEmpty()) {
            var s = widgets.addSlot(this.inputs.get(0), 3, 4);
            s.drawBack(false);
        }

        if (this.inputs.size() > 1) {
            var s = widgets.addSlot(this.inputs.get(1), 21, 4);
            s.drawBack(false);
        }

        if (this.inputs.size() > 2) {
            var s = widgets.addSlot(this.inputs.get(2), 39, 4);
            s.drawBack(false);
        }

        if (this.inputs.size() > 3) {
            var s = widgets.addSlot(this.inputs.get(3), 3, 22);
            s.drawBack(false);
        }

        if (this.inputs.size() > 4) {
            var s = widgets.addSlot(this.inputs.get(4), 21, 22);
            s.drawBack(false);
        }

        if (this.inputs.size() > 5) {
            var s = widgets.addSlot(this.inputs.get(5), 39, 22);
            s.drawBack(false);
        }

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 100, 29);
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