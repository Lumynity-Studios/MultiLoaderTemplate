package net.lumynitystudios.examplemod.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.lumynitystudios.examplemod.client.CommonClient;

public class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CommonClient.init();
    }
}