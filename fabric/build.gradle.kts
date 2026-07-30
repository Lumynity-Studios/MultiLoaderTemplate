import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import multiloader.*
import org.gradle.kotlin.dsl.named

plugins {
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.publish)
}

loom {
    accessWidenerPath.set(file("src/main/resources/${modId}.accesswidener"))
}

val shadowCommon by configurations.getting
dependencies {
    minecraft(libs.minecraft.get())
    implementation(libs.fabric.loader.get())
    implementation(libs.fabric.api.get())

    // Fabric dependencies go here
    //implementation("net.justmili:corelibs:${root.property("corelibs")}+mc${mcVersion}-Fabric")

    implementation("com.terraformersmc:modmenu:${root.property("mod_menu")}")

    // Fabric Loader has MixinExtras built-in since 0.15.0 but not MixinSquared. Uncomment if needed
    //include(implementation(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-fabric:${libs.versions.mixinsquared.get()}")!!)!!)

    shadowCommon(implementation(project(":common")) {
        isTransitive = false
    })
}

tasks {
    processResources {
        filesMatching("fabric.mod.json") {
            expand(mapOf(
                "mod_id" to modId,
                "mod_name" to modName,
                "mod_version" to modVersion,
                "mod_description" to modDesc,
                "mod_authors" to modAuthor,
                "mod_license" to modLicense,
                "fabric_loader_version" to libs.versions.fabric.loader.get(),
                "fabric_api_version" to libs.versions.fabric.api.get(),
                "minecraft_version_constraint" to root.property("minecraft_version_constraint_fabric"),
                "corelibs" to root.property("corelibs")
            ))
        }
    }
}

publishMods {
    file.set(tasks.named<ShadowJar>("shadowJar").get().archiveFile)
    modLoaders.add("fabric")

    changelog = readChangelogFromBranch("origin/rep-info", ".Changelogs/${modVersion}-Changelog.md")

    modrinth {
        accessToken = property("modrinth_token") as String
        projectId = "abcdefg"

        minecraftVersions.add(mcVersion)
        environment = CLIENT_AND_SERVER
        // STABLE, BETA, ALPHA
        type = STABLE

        requires("fabric-api", "millies-core-libs")
    }

    curseforge {
        accessToken = property("curseforge_token") as String
        projectId = "abcdefg"

        minecraftVersions.add(mcVersion)
        client = true
        server = true
        // STABLE, BETA, ALPHA
        type = STABLE

        requires("fabric-api", "millies-core-libs")
    }
}