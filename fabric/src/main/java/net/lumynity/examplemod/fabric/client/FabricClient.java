package net.lumynity.examplemod.fabric.client;

import net.lumynity.examplemod.client.CommonClient;

import net.fabricmc.api.ClientModInitializer;

public class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CommonClient.init();
    }
}