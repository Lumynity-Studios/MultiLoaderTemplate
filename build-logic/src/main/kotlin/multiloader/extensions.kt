package multiloader

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.root: Project
    get() = rootProject

val Project.modId: String
    get() = root.property("mod_id") as String

val Project.modName: String
    get() = root.property("mod_name") as String

val Project.modVersion: String
    get() = root.property("mod_version") as String

val Project.modDesc: String
    get() = root.property("mod_description") as String

val Project.modLicense: String
    get() = root.property("mod_license") as String

val Project.modAuthor: String
    get() = root.property("mod_authors") as String

val Project.baseName: String
    get() = root.property("archives_base_name") as String

val Project.mcVersion: String
    get() = root.extensions.getByType<VersionCatalogsExtension>()
        .find("libs").get()
        .findVersion("minecraft").get()
        .requiredVersion
