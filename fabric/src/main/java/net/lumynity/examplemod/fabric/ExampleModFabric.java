package net.lumynity.examplemod.fabric;

import net.fabricmc.api.ModInitializer;
import net.lumynity.examplemod.ExampleMod;

public final class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ExampleMod.init();
    }
}
