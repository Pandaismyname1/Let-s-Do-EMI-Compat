package com.pandaismyname1.emiletsdocompat.herbalbrews;

import com.pandaismyname1.emiletsdocompat.MockRecipeIdGenerator;
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
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;

import java.util.function.Supplier;

public class CauldronBrewingRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("herbalbrews", "textures/gui/cauldron.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());

    public CauldronBrewingRecipe(EmiRecipeCategory category, net.satisfy.herbalbrews.core.recipe.CauldronRecipe recipe) {
        super(category, MockRecipeIdGenerator.generateRecipeId(), 70, 18);
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        var bgTex = widgets.addTexture(TEXTURE, 0, 0, 118, 68, 52, 11);
        var waterTex = widgets.addAnimatedTexture(TEXTURE, 58, 58, 18, 4, 176, 29, 5000, true, false, false);
        var bubbleTex = widgets.addAnimatedTexture(TEXTURE, 62, 27, 11, 29, 176, 0, 5000, false, true, false);


        var input1 = widgets.addSlot(this.inputs.get(0), 4, 4);
        input1.drawBack(false);

        var input2 = widgets.addSlot(this.inputs.get(1), 26, 10);
        input2.drawBack(false);

        var input3 = widgets.addSlot(this.inputs.get(2), 48, 4);
        input3.drawBack(false);

        var herbalInfusion = widgets.addSlot(EmiIngredient.of(Ingredient.of(ObjectRegistry.HERBAL_INFUSION.get())), 95, 30);
        herbalInfusion.drawBack(false);

        var output = widgets.addSlot(this.outputs.get(0), 26, 46);
        output.drawBack(false);
    }

    @Override
    public int getDisplayHeight() {
        return 68;
    }

    @Override
    public int getDisplayWidth() {
        return 118;
    }
}