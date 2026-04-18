import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    alias(libs.plugins.shadow) apply false
    id("idea")
}

val mcVersion = libs.versions.minecraft.get()

allprojects {
    apply(plugin = "java")
    apply(plugin = "idea")

    version = "${rootProject.property("mod_version")}+${mcVersion}"
    group = rootProject.property("maven_group") as String

    base {
        archivesName.set(rootProject.property("archives_base_name") as String)
    }

    repositories {
        // All repositories used should be listed here
        mavenCentral()
        maven("https://maven.fabricmc.net")
        maven("https://maven.neoforged.net/releases")
        maven("https://maven.minecraftforge.net")
        maven("https://api.modrinth.com/maven")
    }

    val targetJavaVersion = 25

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
        }
    }

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
}

subprojects {
    apply(plugin = "com.gradleup.shadow")

    val shadowCommon by configurations.creating

    tasks {
        jar {
            archiveClassifier.set("slim")
        }

        named<ShadowJar>("shadowJar") {
            configurations = listOf(shadowCommon)
            archiveClassifier.set(null)
        }
    }
}
