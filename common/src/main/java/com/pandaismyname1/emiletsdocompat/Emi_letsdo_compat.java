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
    }
}

