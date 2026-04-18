plugins {
    alias(libs.plugins.fabric.loom)
}

base {
    archivesName.set("${archivesName.get()}")
}

dependencies {
    minecraft(libs.minecraft.get())
    implementation(libs.fabric.loader.get())

    // Common (Fabric/Common) dependencies go here
    //implementation("maven.modrinth:lumynlib:${rootProject.property("lumynlib")}-Fabric")
    //implementation("dev.architectury:architectury:${libs.versions.arch.api.get()}")
    //implementation("com.github.glitchfiend:TerraBlender-fabric:${libs.versions.minecraft.get()}-${rootProject.property("terrablender")}")

    include(implementation(annotationProcessor("io.github.llamalad7:mixinextras-fabric:${libs.versions.mixinextras.get()}")!!)!!)
    include(implementation(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-fabric:${libs.versions.mixinsquared.get()}")!!)!!)
}
