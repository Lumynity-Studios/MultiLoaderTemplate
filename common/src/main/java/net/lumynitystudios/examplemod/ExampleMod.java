package net.lumynitystudios.examplemod;

import net.minecraft.resources.ResourceLocation;

public class ExampleMod {
    public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExampleMod.class);
    public static final String MODID = "examplemod";

    public static void init() {}

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MODID, name);
    }
}
