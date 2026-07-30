import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import multiloader.*

plugins {
    java
    alias(libs.plugins.shadow) apply false
    alias(libs.plugins.fabric.loom) apply false // Gradle wants it. Sure.
    id("idea")
    id("multiloader-extensions")
}

allprojects {
    apply(plugin = "multiloader-extensions")

    repositories {}
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "com.gradleup.shadow")

    base.archivesName.set(baseName)

    // All repositories used should be listed here
    repositories {
        mavenCentral()
        maven("https://maven.fabricmc.net") // Fabric
        maven("https://maven.neoforged.net/releases") // NeoForge
        maven("https://maven.minecraftforge.net/") // Forge

        maven("https://repo.spongepowered.org/repository/maven-public/")
        maven("https://maven.bawnorton.com/releases") // MixinSqured
        maven("https://maven.enjarai.dev/mirrors") // MixinSqured

        maven("https://maven.terraformersmc.com/") // Mod Menu
        maven("https://api.modrinth.com/maven") // Modrinth
        maven("https://maven.lumynitystudios.net/") // Lumynity Studios' mods
    }

    val targetJavaVersion = 25

    java {
        val javaVersion = JavaVersion.toVersion(targetJavaVersion)
        if (JavaVersion.current() < javaVersion) {
            toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
        }
    }

    // IDEA no longer automatically downloads sources/javadoc jars for dependencies, so we need to explicitly enable the behavior.
    idea {
        module {
            isDownloadSources = true
            isDownloadJavadoc = true
        }
    }

    val detectedPlatform = when {
        project.name.contains("fabric", ignoreCase = true) -> "Fabric"
        project.name.contains("neoforge", ignoreCase = true) -> "NeoForge"
        else -> "Common"
    }
    project.version = "${modVersion}+mc${mcVersion}-${detectedPlatform}"

    val shadowCommon by configurations.creating
    tasks {
        withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
            if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
                options.release.set(targetJavaVersion)
            }
        }

        processResources {
            filteringCharset = "UTF-8"
        }

        jar {
            from("LICENSE") {
                rename { it }
            }
            archiveClassifier.set("slim")
            archiveBaseName.set(baseName)
            archiveVersion.set(project.version.toString())
        }

        named<ShadowJar>("shadowJar") {
            configurations = listOf(shadowCommon)
            archiveClassifier.set(null)
            archiveBaseName.set(baseName)
            archiveVersion.set(project.version.toString())
        }
    }
}