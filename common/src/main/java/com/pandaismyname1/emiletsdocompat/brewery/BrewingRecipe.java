package com.pandaismyname1.emiletsdocompat.brewery;

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

public class BrewingRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = new ResourceLocation("brewery", "textures/gui/brewingstation.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public BrewingRecipe(EmiRecipeCategory category, net.satisfy.brewery.recipe.BrewingRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(TEXTURE, -1, -1, 124, 60, 25, 12);
        widgets.addFillingArrow(50, 21, 5000);

        if (this.inputs.size() > 0) {
            widgets.addSlot(this.inputs.get(0), 23, 3).drawBack(false);
        }
        if (this.inputs.size() > 1) {
            widgets.addSlot(this.inputs.get(1), 23, 21).drawBack(false);
        }
        if (this.inputs.size() > 2) {
            widgets.addSlot(this.inputs.get(2), 23, 39).drawBack(false);
        }
        if (this.outputs.size() > 0) {
            widgets.addSlot(this.outputs.get(0), 83, 21).drawBack(false);
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