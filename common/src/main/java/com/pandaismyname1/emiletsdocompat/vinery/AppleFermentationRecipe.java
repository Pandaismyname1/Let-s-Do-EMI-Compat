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
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class AppleFermentationRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = new ResourceLocation("vinery", "textures/gui/apple_press_gui.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public AppleFermentationRecipe(EmiRecipeCategory category, net.satisfy.vinery.core.recipe.ApplePressFermentingRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        var ingredients = recipe.getIngredients();
        var requiresBottle = recipe.requiresBottle();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        if (requiresBottle) {
            var ingredient = Ingredient.of(net.satisfy.vinery.core.registry.ObjectRegistry.WINE_BOTTLE.get());
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(TEXTURE, 15, 20, 36, 18, 100, 49);
        widgets.addTexture(TEXTURE, 66, 15, 10, 28, 101, 17);
        widgets.addAnimatedTexture(TEXTURE, 66, 15, 10, 28, 176, 47, 10000, false, true, false);
        widgets.addTexture(TEXTURE, 89, 20, 18, 18, 118, 17);


        if (!this.inputs.isEmpty()) {
            var s = widgets.addSlot(this.inputs.get(0), 15, 20);
            s.drawBack(false);
        }
        if (this.inputs.size() > 1) {
            var s = widgets.addSlot(this.inputs.get(1), 33, 20);
            s.drawBack(false);
        }

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 89, 20);
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
