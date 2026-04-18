plugins {
    alias(libs.plugins.moddevgradle)
}

base {
    archivesName.set("${archivesName.get()}")
}

val modId = rootProject.property("mod_id") as String

neoForge {
    version = libs.versions.neoforge.get()

    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
        }
    }

    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        // Enable if you want datagen
        /*create("data") {
            clientData()
            programArguments.addAll(
                "--mod", modId,
                "--all",
                "--output", file("src/generated/resources").absolutePath,
                "--existing", rootProject.project(":common").file("src/main/resources").absolutePath
            )
        }*/

        create("server") {
            server()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
    }
}

val shadowCommon by configurations.getting

dependencies {
    shadowCommon(implementation(project(":common")) {
        isTransitive = false
    })

    // NeoForge dependencies go here
    //implementation("maven.modrinth:lumynlib:${rootProject.property("lumynlib")}-NeoForge")
    //implementation("dev.architectury:architectury-neoforge:${libs.versions.arch.api.get()}")
    //implementation("com.github.glitchfiend:TerraBlender-neoforge:${libs.versions.minecraft.get()}-${rootProject.property("terrablender")}")

    // NeoForge mainlines MixinExtras since 1.21.1 but not MixinSquared
    compileOnly(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-common:${libs.versions.mixinsquared.get()}")!!)
    jarJar("com.github.bawnorton.mixinsquared:mixinsquared-neoforge:${libs.versions.mixinsquared.get()}")
}

tasks {
    named<Jar>("shadowJar").get().archiveClassifier = "NeoForge"

    processResources {
        filesMatching("META-INF/neoforge.mods.toml") {
            expand(mapOf(
                "mod_id" to rootProject.property("mod_id"),
                "mod_name" to rootProject.property("mod_name"),
                "mod_version" to rootProject.property("mod_version"),
                "mod_description" to rootProject.property("mod_description"),
                "mod_authors" to rootProject.property("mod_authors"),
                "mod_license" to rootProject.property("mod_license"),
                "neoforge_version" to libs.versions.neoforge.get(),
                "minecraft_version_constraint" to rootProject.property("minecraft_version_constraint_forge"),
            ))
        }
    }
}