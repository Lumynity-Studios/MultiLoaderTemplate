package net.lumynitystudios.examplemod.fabric;

import net.fabricmc.api.ModInitializer;
import net.lumynitystudios.examplemod.ExampleMod;

public final class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ExampleMod.init();
    }
}
