package net.lumynity.examplemod.fabric;

import net.fabricmc.api.ModInitializer;

import net.lumynity.examplemod.LumynLib;

public final class LibFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        LumynLib.init();
    }
}
