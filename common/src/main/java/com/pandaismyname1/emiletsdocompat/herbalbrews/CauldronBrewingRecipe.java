package com.pandaismyname1.emiletsdocompat.herbalbrews;

import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class CauldronBrewingRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = new ResourceLocation("herbalbrews", "textures/gui/cauldron.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public CauldronBrewingRecipe(EmiRecipeCategory category, satisfy.herbalbrews.recipe.CauldronRecipe recipe) {
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
        widgets.addFillingArrow(67, 20, 5000);


        if (!this.inputs.isEmpty()) {
            var s = widgets.addSlot(this.inputs.get(0), 6, 13);
            s.drawBack(false);
        }

        if (this.inputs.size() > 1) {
            var s = widgets.addSlot(this.inputs.get(1), 24, 13);
            s.drawBack(false);
        }

        if (this.inputs.size() > 2) {
            var s = widgets.addSlot(this.inputs.get(2), 6, 31);
            s.drawBack(false);
        }

        if (this.inputs.size() > 3) {
            var s = widgets.addSlot(this.inputs.get(3), 24, 31);
            s.drawBack(false);
        }

        var bSlot = widgets.addSlot(EmiIngredient.of(Ingredient.of(Items.GLASS_BOTTLE)), 52, 38);
        bSlot.drawBack(false);

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 101, 22);
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