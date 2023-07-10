import net.kyori.indra.git.IndraGitExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.attributes
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.support.uppercaseFirstChar
import org.gradle.kotlin.dsl.the
import java.text.SimpleDateFormat
import java.util.*

val Project.libs: LibrariesForLibs
    get() = the()

// Hangar has it like this but, Modrinth will want it lowercase
fun Project.channel(): String {
    return if (this.versionString().endsWith("-SNAPSHOT")) {
        "Beta"
    } else {
        "Release"
    }
}

fun Project.versionString(): String = this.version as String

fun Project.nameString(): String = if (this.name.contains('-')) {
    this.name.split("-").joinToString("-") { it.uppercaseFirstChar() }
} else {
    this.name.uppercaseFirstChar()
}

fun Project.applyJarMetadata(moduleName: String) {
    if ("jar" in tasks.names) {
        tasks.named<Jar>("jar") {
            manifest.attributes(
                "Multi-Release" to "true",
                "Built-By" to System.getProperty("user.name"),
                "Created-By" to System.getProperty("java.vendor.version"),
                "Build-Jdk" to System.getProperty("java.version"),
                "Build-Time" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm.sssZ").format(Date()),
                "Automatic-Module-Name" to moduleName,
                "Specification-Title" to moduleName,
                "Specification-Version" to project.versionString(),
                "Specification-Vendor" to providers.gradleProperty("projectAuthor").getOrElse("Template"),
            )
            val indraGit = project.extensions.findByType<IndraGitExtension>()
            indraGit?.applyVcsInformationToManifest(manifest)
        }
    }
}
