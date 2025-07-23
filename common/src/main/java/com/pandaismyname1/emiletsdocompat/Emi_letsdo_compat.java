package com.pandaismyname1.emiletsdocompat;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.minecraft.world.item.crafting.RecipeManager;

@EmiEntrypoint
public class Emi_letsdo_compat implements EmiPlugin {
    public static final String MOD_ID = "emi_letsdo_compat";

    @Override
    public void register(EmiRegistry registry) {
        RecipeManager manager = registry.getRecipeManager();

        // Compats:
        // Bakery, Beachparty, Brewery, Candlelight, Farm And Charm, Hebalbrews, Meadow, Vinery

        try {
            Class.forName("net.satisfy.bakery.Bakery");
            Class.forName("net.satisfy.bakery.recipe.BakingStationRecipe");
            new com.pandaismyname1.emiletsdocompat.bakery.BakeryCompat().init(registry, manager);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchFieldError e) {
            // Bakery not found
        }

        try {
            Class.forName("satisfy.beachparty.Beachparty");
            new com.pandaismyname1.emiletsdocompat.beachparty.BeachpartyCompat().init(registry, manager);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchFieldError e) {
            // Beachparty not found
        }

        try {
            Class.forName("net.satisfy.brewery.Brewery");
            Class.forName("net.satisfy.brewery.recipe.BrewingRecipe");
            new com.pandaismyname1.emiletsdocompat.brewery.BreweryCompat().init(registry, manager);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchFieldError e) {
            // Brewery not found
        }

        try {
            Class.forName("net.satisfy.candlelight.Candlelight");
            Class.forName("net.satisfy.candlelight.recipe.CookingPanRecipe");
            new com.pandaismyname1.emiletsdocompat.candlelight.CandlelightCompat().init(registry, manager);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchFieldError e) {
            // Candlelight not found
        }

        try {
            Class.forName("net.satisfy.farm_and_charm.FarmAndCharm");
            new com.pandaismyname1.emiletsdocompat.farm_and_charm.FarmAndCharmCompat().init(registry, manager);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchFieldError e) {
            // Farmandcharm not found
        }

        try {
            Class.forName("satisfy.herbalbrews.HerbalBrews");
            new com.pandaismyname1.emiletsdocompat.herbalbrews.HerbalbrewsCompat().init(registry, manager);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchFieldError e) {
            // Herbalbrews not found
        }

        try {
            Class.forName("net.satisfy.meadow.Meadow");
            new com.pandaismyname1.emiletsdocompat.meadow.MeadowCompat().init(registry, manager);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchFieldError e) {
            // Meadow not found
        }

        try {
            Class.forName("net.satisfy.vinery.core.Vinery");
            new com.pandaismyname1.emiletsdocompat.vinery.VineryCompat().init(registry, manager);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchFieldError e) {
            // Vinery not found
        }

    }
}
