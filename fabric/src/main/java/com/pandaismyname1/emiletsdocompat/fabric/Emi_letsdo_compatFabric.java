package com.pandaismyname1.emiletsdocompat.fabric;

import com.pandaismyname1.emiletsdocompat.Emi_letsdo_compat;
import net.fabricmc.api.ModInitializer;

public class Emi_letsdo_compatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Emi_letsdo_compat.init();
    }
}