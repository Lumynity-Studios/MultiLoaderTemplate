package net.lumynitystudios.examplemod.forge.client;

import net.lumynitystudios.examplemod.ExampleMod;
import net.lumynitystudios.examplemod.client.CommonClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = ExampleMod.MODID, value = Dist.CLIENT)
public class NeoClient {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        CommonClient.init();
    }
}