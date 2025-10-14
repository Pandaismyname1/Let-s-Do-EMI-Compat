package com.pandaismyname1.emiletsdocompat.neoforge;

import com.pandaismyname1.emiletsdocompat.Emi_letsdo_compat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Emi_letsdo_compat.MOD_ID)
public final class Emi_letsdo_compatNeoForge {
    public Emi_letsdo_compatNeoForge(IEventBus bus, ModContainer modContainer) {
        Emi_letsdo_compat.init();
    }
}