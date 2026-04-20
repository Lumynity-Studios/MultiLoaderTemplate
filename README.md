# Lumynity Studios' ML Mod Template
<sub>Repository branch based off of [LumynLib](https://github.com/Lumynity-Studios/LumynLib)</sub>

Everything you'd want to update is either in `gradle.properties` or in `gradle/libs.versions.toml`.
Any dependencies you want to add should be done in their corresponding submodules for their
respective mod loaders.

## Developer Notes
- The output JAR always follows the format of `ModName-version+mc[minecraft_version]-Platform.jar`, for example `AlwaysShield-1.2.1+mc26.1-Fabric.jar`.
  - If there exists a classifier, that is not your intended output JAR
- If you want to compile a JAR for all mods, you should run the `build` or `assemble` task.
- To remove any loader from the project, simply remove it from `include("common", "fabric", "quilt", "forge")` in `settings.gradle.kts`
- Launching Forge and NeoForge in a development environment doesn't work.
  Some common resources such as mixins don't carry over to Forge when running development environment which makes Forge refuse to run.
- If you entirely don't want datagen, you can delete `architectury.common.json`, `examplemod.accesswidener`, and `template.accesswidener` files.