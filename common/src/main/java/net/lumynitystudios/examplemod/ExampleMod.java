package net.lumynitystudios.examplemod;

import net.minecraft.resources.ResourceLocation;

public class ExampleMod {
    public static final String MOD_ID = "examplemod";

    public static void init() {}

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
