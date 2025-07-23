package com.pandaismyname1.emiletsdocompat.forge;

import com.pandaismyname1.emiletsdocompat.Emi_letsdo_compat;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Emi_letsdo_compat.MOD_ID)
public class Emi_letsdo_compatForge {
    public Emi_letsdo_compatForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Emi_letsdo_compat.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
    }
}