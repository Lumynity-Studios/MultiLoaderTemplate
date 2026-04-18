package net.lumynity.examplemod;

import net.minecraft.resources.Identifier;

public class ExampleMod {
    public static final String MOD_ID = "examplemod";

    public static void init() {}

    public static Identifier asResource(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}
