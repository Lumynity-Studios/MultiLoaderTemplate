import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.api.fabricapi.FabricApiExtension

plugins {
    java
    alias(libs.plugins.arch.loom) apply false
    alias(libs.plugins.arch.plugin)
    alias(libs.plugins.shadow) apply false
}

val mcVersion = libs.versions.minecraft.get()
val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
val fabricApi = project.extensions.getByName<FabricApiExtension>("fabricApi")

architectury {
    minecraft = mcVersion
}

allprojects {
    group = rootProject.property("maven_group") as String
    version = rootProject.property("mod_version") as String

    base { archivesName.set(rootProject.property("archives_base_name") as String) }

    repositories {
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "${libs.plugins.arch.loom}")
    apply(plugin = "${libs.plugins.arch.plugin}")

    repositories {
        maven("https://maven.parchmentmc.org")

        maven("https://maven.minecraftforge.net/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
        
        maven("https://jitpack.io")
        maven("https://maven.terraformersmc.com/")
    }

    loom.silentMojangMappingsLicense()

    dependencies {
        "minecraft"(libs.minecraft.get())

        /* Uncomment if you want datagen (fabric)
        // Only for the sake of compiling - not to be used for anything else!
        "modCompileOnly"(fabricApi.module("fabric-recipe-api-v1", libs.versions.fabric.api.get()))
         */

        "mappings"(loom.layered() {
            officialMojangMappings()
            "parchment"("org.parchmentmc.data:parchment-${mcVersion}:${libs.versions.parchment.get()}@zip")
        })
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(17)
    }

    val detectedPlatform = when {
        project.name.contains("fabric", ignoreCase = true) -> "Fabric"
        project.name.contains("forge", ignoreCase = true) -> "Forge"
        else -> null
    }
    project.version = if (detectedPlatform != null) {
        "${rootProject.property("mod_version")}+mc${mcVersion}-${detectedPlatform}"
    } else {
        rootProject.property("mod_version") as String
    }

    tasks.withType<Jar>().configureEach {
        archiveBaseName.set(rootProject.property("archives_base_name") as String)
        archiveVersion.set(project.version.toString())
    }

    // From this comment and below: TO FIX
    tasks.matching { it.name == "shadowJar" }.configureEach {
        if (hasProperty("archiveBaseName")) {
            // Gradle is complaining abt this - TO FIX
            it.archiveBaseName.set(rootProject.property("archives_base_name"))
            it.archiveVersion.set(project.version.toString())
        }
    }
}