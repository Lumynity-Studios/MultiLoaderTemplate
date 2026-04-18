# Lumynity Studios' ML Mod Template
<sub>Forked from [Naz's Multiloader Template](https://github.com/BluSpring/MultiLoaderTemplate)</sub>

A 26.1+ Mod & Gradle Project Template for Fabric, Forge and NeoForge.
If you try to make it work with older Minecraft versions, you *will* suffer.

Everything you'd want to update is either in `gradle.properties` or in `gradle/libs.versions.toml`.
Any dependencies you want to add should be done in their corresponding submodules for their
respective mod loaders.

## Developer Notes
- The `common` and `forge` mixin JSONs need to use the `JAVA_21` compatibility level. Forge has no idea how to handle the Java 25 compatibility level because
  it doesn't use Fabric's fork of mixin. Fabric and NeoForge are fine.
- The output JAR always follows the format of `ModName-version+mc[minecraft_version]-Platform.jar`, for example `AlwaysShield-1.2.1+mc26.1-Fabric.jar`.
  - If there exists a classifier, that is not your intended output JAR.
- Maven publishing is unavailable, we generally have a whole process behind publishing any new mods or updates so we don't really need maven publishing.
- If you want to compile a JAR for all mods, you should run the `build` or `assemble` task.
- To remove any loader from the project, simply remove it from `include("common", "fabric", "forge", "neoforge")` in `settings.gradle.kts`
- Launching Forge and NeoForge in a development environment doesn't work. We don't know why. Feel free to try to fix it,
  and drop a PR at [Naz's Multiloader Template](https://github.com/BluSpring/MultiLoaderTemplate) or here when you do please.
