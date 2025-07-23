package com.pandaismyname1.emiletsdocompat.farm_and_charm;

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

public class CraftingBowlRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = new ResourceLocation("farm_and_charm", "textures/gui/crafting_bowl.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public CraftingBowlRecipe(EmiRecipeCategory category, net.satisfy.farm_and_charm.core.recipe.CraftingBowlRecipe recipe) {
        super(category, recipe.getId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(TEXTURE, 0, 0, 124, 60, 16, 12);
        widgets.addFillingArrow(62, 24, 5000);


        if (!this.inputs.isEmpty()) {
            var s = widgets.addSlot(this.inputs.get(0), 13, 12);
            s.drawBack(false);
        }

        if (this.inputs.size() > 1) {
            var s = widgets.addSlot(this.inputs.get(1), 33, 12);
            s.drawBack(false);
        }

        if (this.inputs.size() > 2) {
            var s = widgets.addSlot(this.inputs.get(2), 13, 30);
            s.drawBack(false);
        }

        if (this.inputs.size() > 3) {
            var s = widgets.addSlot(this.inputs.get(3), 33, 30);
            s.drawBack(false);
        }

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 93, 22);
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
