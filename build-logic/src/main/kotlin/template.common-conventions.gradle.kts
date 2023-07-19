import com.diffplug.gradle.spotless.FormatExtension
import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer
import net.kyori.indra.licenser.spotless.HeaderFormat
import java.util.*

plugins {
    id("template.base-conventions")
    id("net.kyori.indra.crossdoc")
    id("net.kyori.indra")
    id("net.kyori.indra.git")
    id("net.kyori.indra.licenser.spotless")
    id("com.github.johnrengelman.shadow")
    id("java-library")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

indra {
    javaVersions {
        minimumToolchain(17)
        target(Constants.JAVA_VERSION)
    }

    publishSnapshotsTo("drink", "https://new-repo.deltapvp.net/snapshots")
    publishReleasesTo("drink", "https://new-repo.deltapvp.net/releases")
}

java {
    withSourcesJar()
    withJavadocJar()
}

spotless {
    fun FormatExtension.applyCommon() {
        trimTrailingWhitespace()
        endWithNewline()
        encoding("UTF-8")
        toggleOffOn()
    }
    java {
        importOrderFile(rootProject.file(".spotless/delta.importorder"))
        removeUnusedImports()
        formatAnnotations()
        applyCommon()
    }
    kotlinGradle {
        applyCommon()
    }
    kotlin {
        applyCommon()
    }
    json {
        applyCommon()
        target("*/src/*/resources/**/*.json")
    }
    yaml {
        applyCommon()
        target("*/src/*/resources/**/*.yml")
    }
}

indraSpotlessLicenser {
    headerFormat(HeaderFormat.starSlash())
    licenseHeaderFile(rootProject.projectDir.resolve("HEADER"))

    val currentYear = Calendar.getInstance().apply {
        time = Date()
    }.get(Calendar.YEAR)
    val createdYear = providers.gradleProperty("createdYear").map { it.toInt() }.getOrElse(currentYear)
    val year = if (createdYear == currentYear) createdYear.toString() else "$createdYear-$currentYear"

    property("name", providers.gradleProperty("projectName").getOrElse("athena"))
    property("year", year)
    property("description", project.description ?: "A athena project")
    property("author", providers.gradleProperty("projectAuthor").getOrElse("athena"))

}

val PROJECT_PREFIX = "${providers.gradleProperty("projectName").getOrElse("athena").lowercase()}-"
indraCrossdoc {
    baseUrl().set(providers.gradleProperty("javadocPublishRoot"))
    nameBasedDocumentationUrlProvider {
        projectNamePrefix.set(PROJECT_PREFIX)
    }
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveClassifier.set("")
        relocationPrefix = "org.mineorbit.athena.libs"
        isEnableRelocation = false
        // or
        if (!isEnableRelocation) setOf(
            "your-mother"
        ).forEach {
            relocate(it, this.relocationPrefix + "." + it)
        }

        dependencies {
            exclude(dependency("org.jetbrains:annotations:.*"))
            exclude(dependency("org.checkerframework:checker-qual:.*"))
            exclude(dependency("com.google.errorprone:error_prone_core:.*"))
            exclude(dependency("com.google.errorprone:error_prone_annotations:.*"))
            exclude(dependency("com.google.errorprone:error_prone_annotation:.*"))
            exclude(dependency("com.google.code.findbugs:jsr305:.*"))
            exclude(dependency("com.google.j2objc:j2objc-annotations:.*"))
            exclude("org/jetbrains/annotations/*")
            exclude("org/intellij/lang/annotations/*")
        }

        mergeServiceFiles()

        from(rootProject.projectDir.resolve("LICENSE")) {
            rename { "LICENSE_${providers.gradleProperty("projectName").getOrElse("athena")}" }
        }
        archiveBaseName.set(project.nameString())
        transform(Log4j2PluginsCacheFileTransformer::class.java)
    }

    jar {
        archiveClassifier.set("unshaded")
        archiveBaseName.set(project.nameString())
    }

    sourcesJar {
        archiveClassifier.set("sources")
        archiveBaseName.set(project.nameString())
    }

    javadocJar {
        archiveClassifier.set("javadoc")
        archiveBaseName.set(project.nameString())
    }

    withType<JavaCompile>().configureEach {
        options.isFork = true
        options.isIncremental = true
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
        options.compilerArgs.add("-Xlint:-processing")
    }

    withType<ProcessResources>().configureEach {
        filteringCharset = "UTF-8"
        duplicatesStrategy = DuplicatesStrategy.INCLUDE

        val praps = mapOf(
            "pluginVersion" to project.versionString(),
            "pluginAuthor" to providers.gradleProperty("projectAuthor").getOrElse("athena"),
            "pluginName" to providers.gradleProperty("projectName").getOrElse("athena"),
            "pluginDescription" to (project.description ?: "A athena project")
        )

        filesMatching(setOf("paper-plugin.yml", "plugin.yml", "velocity-plugin.json")) {
            expand(praps)
        }
    }

    if (providers.gradleProperty("disableJavadoc").map { it.toBoolean() }.getOrElse(false)) {
        sequenceOf(javadocJar, javadoc, sourcesJar).forEach {
            it.configure {
                onlyIf { false }
            }
        }
    } else {
        javadoc {
            val options = options as? StandardJavadocDocletOptions ?: return@javadoc
            options.tags(
                "sinceMinecraft:a:Since Minecraft:",
                "apiSpec:a:API Requirements:",
                "apiNote:a:API Note:",
                "implSpec:a:Implementation Requirements:",
                "implNote:a:Implementation Note:"
            )
            options.links(
//                "https://javadoc.io/doc/org.jetbrains/annotations/${libs.versions.annotations}/",
                "https://jd.papermc.io/paper/1.20/",
                "https://jd.papermc.io/velocity/3.0.0/",
            )
            options.isAuthor = true
            options.encoding = "UTF-8"
            options.charSet = "UTF-8"
            options.linkSource(true)
        }
    }
}

configurations {
    getting {
        excludeUseless()
    }
}