package net.lumynitystudios.examplemod.forge;

import net.lumynitystudios.examplemod.ExampleMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ExampleMod.MODID)
public final class ExampleModNeo {
    public ExampleModNeo(IEventBus modEventBus) {
        ExampleMod.init();
    }
}
