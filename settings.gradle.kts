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

includeBuild("build-logic")
include("common", "fabric", "neoforge")

rootProject.name = "ExampleMod"