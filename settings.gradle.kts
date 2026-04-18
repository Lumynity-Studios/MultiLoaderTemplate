pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases")
        gradlePluginPortal()
    }
}

include("common", "fabric", "forge", "neoforge")

rootProject.name = "ExampleMod"