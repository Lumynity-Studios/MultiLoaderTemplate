import multiloader.*

plugins {
    alias(libs.plugins.fabric.loom)
}

loom {
    accessWidenerPath.set(root.file("fabric/src/main/resources/${modId}.accesswidener"))
}

dependencies {
    minecraft(libs.minecraft.get())
    // Do NOT use anything from Fabric Loader
    implementation(libs.fabric.loader.get())

    // Common (Fabric/Common) dependencies go here
    //implementation("net.justmili:corelibs:${root.property("corelibs")}+mc${mcVersion}-Fabric")

    //include(implementation(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-fabric:${libs.versions.mixinsquared.get()}")!!)!!)
}
