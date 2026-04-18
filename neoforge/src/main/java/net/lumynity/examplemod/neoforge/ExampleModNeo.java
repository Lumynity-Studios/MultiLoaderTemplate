package net.lumynity.examplemod.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.lumynity.examplemod.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public class ExampleModNeo {
    public ExampleModNeo(IEventBus bus, ModContainer container) {
        ExampleMod.init();
    }
}
