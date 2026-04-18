package net.lumynity.examplemod.neoforge.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.lumynity.examplemod.ExampleMod;
import net.lumynity.examplemod.client.CommonClient;

@Mod(value = ExampleMod.MOD_ID, dist = Dist.CLIENT)
public class NeoClient {
    public NeoClient(IEventBus bus, ModContainer container) {
        CommonClient.init();
    }
}
