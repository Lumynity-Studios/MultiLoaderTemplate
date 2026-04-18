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
    //implementation("dev.architectury:architectury:${rootProject.property("architectury_api")}")
    //implementation("com.github.glitchfiend:TerraBlender-fabric:${libs.versions.minecraft.get()}-${rootProject.property("terrablender")}")
}
