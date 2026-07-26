package com.pandaismyname1.emiletsdocompat.vinery;

import com.pandaismyname1.emiletsdocompat.MockRecipeIdGenerator;
import com.pandaismyname1.emiletsdocompat.vinery.internal.JuiceIngredients;
import dev.architectury.utils.EnvExecutor;
import dev.architectury.utils.GameInstance;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.satisfy.vinery.platform.PlatformHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class FermentationRecipe extends BasicEmiRecipe {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("vinery", "textures/gui/fermentation_barrel_gui.png");
    protected static final Supplier<RegistryAccess> REGISTRY_ACCESS =
            EnvExecutor.getEnvSpecific(() -> () -> () -> GameInstance.getClient().player.level().registryAccess(),
                    () -> () -> () -> GameInstance.getServer().registryAccess());
    private final String juiceType;
    private final int fluidLevel;
    private final boolean requiresBottle;
    /**
     * The juice the barrel has to be filled with. Vinery treats it like a fuel rather than an
     * ingredient, so it is tracked separately from {@link #inputs} indices used by the item slots.
     */
    private final EmiIngredient juice;
    /** The three ingredient slots along the bottom of the barrel, without the juice. */
    private final List<EmiIngredient> itemInputs = new ArrayList<>();

    public FermentationRecipe(EmiRecipeCategory category, net.satisfy.vinery.core.recipe.FermentationBarrelRecipe recipe) {
        super(category, MockRecipeIdGenerator.generateRecipeId(), 70, 18);
        this.juiceType = recipe.getJuiceData().type();
        this.fluidLevel = recipe.getJuiceData().amount();
        this.requiresBottle = recipe.isWineBottleRequired();
        this.juice = JuiceIngredients.of(this.juiceType,
                JuiceIngredients.bottlesFor(this.fluidLevel, PlatformHelper.getMaxFluidIncrease()));
        var ingredients = recipe.getIngredients();
        for (var ingredient : ingredients) {
            this.itemInputs.add(EmiIngredient.of(ingredient));
        }
        this.inputs.addAll(this.itemInputs);
        // Listed as an input so that "Uses" on a grapejuice finds this recipe.
        if (!this.juice.isEmpty()) {
            this.inputs.add(this.juice);
        }
        this.outputs.add(EmiStack.of(recipe.getResultItem(REGISTRY_ACCESS.get())));
    }

    private Component getFluidTooltip(String juiceType, int fluidLevel) {
        int maxFluidLevel = PlatformHelper.getMaxFluidLevel();
        double percentage = (double) fluidLevel / (double) maxFluidLevel * (double) 100.0F;
        String percentageStr = String.format("%.2f", percentage);
        if (juiceType.startsWith("red")) {
            String region = juiceType.substring(4);
            return Component.translatable("tooltip.vinery.fermentation_barrel.red_" + region + "_juice_with_percentage", new Object[]{percentageStr});
        } else if (juiceType.startsWith("white")) {
            String region = juiceType.substring(6);
            return Component.translatable("tooltip.vinery.fermentation_barrel.white_" + region + "_juice_with_percentage", new Object[]{percentageStr});
        } else {
            return juiceType.equals("apple") ? Component.translatable("tooltip.vinery.fermentation_barrel.apple_juice_with_percentage", new Object[]{percentageStr}) : Component.translatable("tooltip.vinery.fermentation_barrel.empty");
        }
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(TEXTURE, 0, 0, 124, 72, 26, 6);
        widgets.addAnimatedTexture(TEXTURE, 96, 14, 10, 28, 176, 0, 20000, false, true, false);
        widgets.addAnimatedTexture(TEXTURE, 56, 38, 20, 4, 176, 37, 30000, true, false, true);

        var ttList = new ArrayList<Component>();
        ttList.add(getFluidTooltip(juiceType, fluidLevel));
        widgets.addTooltipText(ttList, 56, 38, 20, 4);

        // The barrel's juice input slot, above the pipe feeding the fluid bar.
        if (!this.juice.isEmpty()) {
            var s = widgets.addSlot(this.juice, 12, 10);
            s.drawBack(false);
            s.appendTooltip(getFluidTooltip(juiceType, fluidLevel));
        }

        if (!this.itemInputs.isEmpty()) {
            var s = widgets.addSlot(this.itemInputs.get(0), 40, 51);
            s.drawBack(false);
        }
        if (this.itemInputs.size() > 1) {
            var s = widgets.addSlot(this.itemInputs.get(1), 58, 51);
            s.drawBack(false);
        }
        if (this.itemInputs.size() > 2) {
            var s = widgets.addSlot(this.itemInputs.get(2), 76, 51);
            s.drawBack(false);
        }

        if (requiresBottle) {
            var s = widgets.addSlot(EmiIngredient.of(Ingredient.of(net.satisfy.vinery.core.registry.ObjectRegistry.WINE_BOTTLE.get())), 96, 51);
            s.drawBack(false);
        }

        if (!this.outputs.isEmpty()) {
            var s = widgets.addSlot(this.outputs.get(0), 76, 10);
            s.drawBack(false);
        }
    }

    @Override
    public int getDisplayHeight() {
        return 75;
    }

    @Override
    public int getDisplayWidth() {
        return 124;
    }
}