package net.lumynity.examplemod.forge;

import net.lumynity.examplemod.ExampleMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModForge {
    public static IEventBus EVENT_BUS;

    public ExampleModForge(FMLJavaModLoadingContext modContext) {
        MinecraftForge.EVENT_BUS.register(this);
        EVENT_BUS = modContext.getModEventBus();

        ExampleMod.init();
    }
}
