import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecraftforge.jarjar.gradle.JarJar

plugins {
    alias(libs.plugins.forge.gradle)
    alias(libs.plugins.forge.jarjar)
}

base {
    archivesName.set("${archivesName.get()}")
}

val modId = rootProject.property("mod_id") as String

minecraft {
    mappings("official", libs.versions.minecraft.get())

    runs {
        configureEach {
            workingDir.convention(layout.projectDirectory.dir("run"))
            args("--mixin.config=$modId.mixins.json")
            args("--mixin.config=$modId-forge.mixins.json")
        }

        register("client") {
            systemProperty("forge.enabledGameTestNamespaces", modId)
        }
    }
}

repositories {
    // .... unless you're Forge who wants to be special
    minecraft.mavenizer(this)
    maven(fg.forgeMaven)
    maven(fg.minecraftLibsMaven)
}

jarJar.register()

val shadowCommon by configurations.getting

dependencies {
    implementation(minecraft.dependency("net.minecraftforge:forge:${libs.versions.minecraft.get()}-${libs.versions.forge.asProvider().get()}"))
    annotationProcessor("org.spongepowered:mixin:0.8.7:processor")

    shadowCommon(implementation(project(":common")) {
        isTransitive = false
    })

    // Forge dependencies go here
    // LumynLib doesn't support Forge since 1.21.1
    // Architectury API doesn't support Forge since 1.20.4
    //implementation("com.github.glitchfiend:TerraBlender-fabric:${libs.versions.minecraft.get()}-${rootProject.property("terrablender")}")
}

tasks {
    jar {
        archiveClassifier.set("slim")

        manifest {
            attributes["MixinConfigs"] = "$modId.mixins.json,$modId-forge.mixins.json"
        }
    }

    named<ShadowJar>("shadowJar") {
        dependsOn("jarJar")
        archiveClassifier.set(null)
        with(named<JarJar>("jarJar").get())
    }

    named<Jar>("shadowJar").get().archiveClassifier = "Forge"

    named<JarJar>("jarJar") {
        finalizedBy("shadowJar")
        archiveClassifier.set("fat")
    }

    processResources {
        filesMatching("META-INF/mods.toml") {
            expand(mapOf(
                "mod_id" to rootProject.property("mod_id"),
                "mod_name" to rootProject.property("mod_name"),
                "mod_version" to rootProject.property("mod_version"),
                "mod_description" to rootProject.property("mod_description"),
                "mod_authors" to rootProject.property("mod_authors"),
                "mod_license" to rootProject.property("mod_license"),
                "forge_version" to libs.versions.forge.asProvider().get(),
                "minecraft_version_constraint" to rootProject.property("minecraft_version_constraint_forge"),
            ))
        }
    }
}
