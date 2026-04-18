plugins {
    alias(libs.plugins.fabric.loom)
}

base {
    archivesName.set("${archivesName.get()}")
}

val shadowCommon by configurations.getting

repositories {
    maven("https://maven.terraformersmc.com/")
}

dependencies {
    minecraft(libs.minecraft.get())
    implementation(libs.fabric.loader.get())
    implementation(libs.fabric.api.get())

    shadowCommon(implementation(project(":common")) {
        isTransitive = false
    })

    // Fabric dependencies go here
    //implementation("maven.modrinth:lumynlib:${rootProject.property("lumynlib")}-Fabric")
    //implementation("dev.architectury:architectury-fabric:${libs.versions.arch.api.get()}")
    //implementation("com.github.glitchfiend:TerraBlender-fabric:${libs.versions.minecraft.get()}-${rootProject.property("terrablender")}")

    implementation("com.terraformersmc:modmenu:${rootProject.property("mod_menu")}")

    // Fabric Loader has MixinExtras built-in since 0.15.0 but not MixinSquared
    //include(implementation(annotationProcessor("io.github.llamalad7:mixinextras-fabric:${libs.versions.mixinextras.get()}")!!)!!)
    include(implementation(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-fabric:${libs.versions.mixinsquared.get()}")!!)!!)
}

tasks {
    named<Jar>("shadowJar").get().archiveClassifier = "Fabric"

    processResources {
        filesMatching("fabric.mod.json") {
            expand(mapOf(
                "mod_id" to rootProject.property("mod_id"),
                "mod_name" to rootProject.property("mod_name"),
                "mod_version" to rootProject.property("mod_version"),
                "mod_description" to rootProject.property("mod_description"),
                "mod_authors" to rootProject.property("mod_authors"),
                "mod_license" to rootProject.property("mod_license"),
                "fabric_loader_version" to libs.versions.fabric.loader.get(),
                "fabric_api_version" to libs.versions.fabric.api.get(),
                "minecraft_version_constraint" to rootProject.property("minecraft_version_constraint_fabric"),
            ))
        }
    }
}