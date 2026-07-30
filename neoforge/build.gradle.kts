import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import multiloader.*

plugins {
    alias(libs.plugins.moddevgradle)
    alias(libs.plugins.publish)
}

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

        create("data") {
            clientData()
            programArguments.addAll(
                "--mod", modId,
                "--all",
                "--output", file("src/generated/resources").absolutePath,
                "--existing", root.project(":common").file("src/main/resources").absolutePath
            )
        }

        create("server") {
            server()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
    }
}

val shadowCommon by configurations.getting
dependencies {
    // NeoForge dependencies go here
    //implementation("net.justmili:corelibs:${root.property("corelibs")}+mc${mcVersion}-NeoForge")

    // NeoForge mainlines MixinExtras since 1.21.1 but not MixinSquared. Uncomment if needed
    //compileOnly(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-common:${libs.versions.mixinsquared.get()}")!!)
    //jarJar("com.github.bawnorton.mixinsquared:mixinsquared-neoforge:${libs.versions.mixinsquared.get()}")

    shadowCommon(implementation(project(":common")) {
        isTransitive = false
    })
}

tasks {
    processResources {
        filesMatching("META-INF/neoforge.mods.toml") {
            expand(mapOf(
                "mod_id" to modId,
                "mod_name" to modName,
                "mod_version" to modVersion,
                "mod_description" to modDesc,
                "mod_authors" to modAuthor,
                "mod_license" to modLicense,
                "neoforge_version" to libs.versions.neoforge.get(),
                "minecraft_version_constraint" to root.property("minecraft_version_constraint_forge"),
                "corelibs" to root.property("corelibs")
            ))
        }
    }
}

publishMods {
    file.set(tasks.named<ShadowJar>("shadowJar").get().archiveFile)
    modLoaders.add("neoforge")

    changelog = readChangelogFromBranch("origin/rep-info", ".Changelogs/${modVersion}-Changelog.md")

    modrinth {
        accessToken = property("modrinth_token") as String
        projectId = "abcdefg"

        minecraftVersions.add(mcVersion)
        environment = CLIENT_AND_SERVER
        // STABLE, BETA, ALPHA
        type = STABLE

        requires("millies-core-libs")
    }

    curseforge {
        accessToken = property("curseforge_token") as String
        projectId = "abcdefg"

        minecraftVersions.add(mcVersion)
        client = true
        server = true
        // STABLE, BETA, ALPHA
        type = STABLE

        requires("millies-core-libs")
    }
}