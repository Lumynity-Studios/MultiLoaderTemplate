package net.lumynity.examplemod.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.lumynity.examplemod.ExampleMod;
import net.lumynity.examplemod.client.CommonClient;

@Mod(ExampleMod.MOD_ID)
public class ExampleModForge {
    public ExampleModForge(FMLJavaModLoadingContext context) {
        ExampleMod.init();
    }
}
