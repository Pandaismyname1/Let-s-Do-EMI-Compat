package com.pandaismyname1.emiletsdocompat;

public class Emi_letsdo_compat {
    public static final String MOD_ID = "emi_letsdo_compat";

    public static void init() {
        try {
            Class.forName("net.satisfy.meadow.Meadow");
            com.pandaismyname1.emiletsdocompat.meadow.internal.Registry.init();
        } catch (ClassNotFoundException | NoSuchFieldError e) {
            // Meadow not found
        }


        try {
            Class.forName("net.satisfy.bakery.Bakery");
            Class.forName("net.satisfy.bakery.recipe.BakingStationRecipe");
            com.pandaismyname1.emiletsdocompat.bakery.internal.Registry.init();
        } catch (ClassNotFoundException | NoSuchFieldError e) {
            // Bakery not found
        }

        try {
            Class.forName("net.satisfy.bakery.Bakery");
            Class.forName("net.satisfy.bakery.core.recipe.BakingStationRecipe");
            com.pandaismyname1.emiletsdocompat.bakery.internal.Registry.init();
        } catch (ClassNotFoundException | NoSuchFieldError e) {
            // Bakery not found
        }

        try {
            Class.forName("net.satisfy.vinery.Vinery");
            com.pandaismyname1.emiletsdocompat.vinery.internal.Registry.init();
        } catch (ClassNotFoundException | NoSuchFieldError e) {
            // Vinery not found
        }
    }
}

