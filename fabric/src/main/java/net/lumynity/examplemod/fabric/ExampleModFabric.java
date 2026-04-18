package net.lumynity.examplemod.fabric;

import net.lumynity.examplemod.ExampleMod;

import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ExampleMod.init();
    }
}
