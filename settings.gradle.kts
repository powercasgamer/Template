pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.deltapvp.net")
        maven(url = "https://repo.stellardrift.ca/repository/snapshots/") {
            name = "stellardriftSnapshots"
            mavenContent { snapshotsOnly() }
        }
	mavenLocal() 
   }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.6.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    // repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven("https://maven.deltapvp.net")
	mavenLocal()
    }
}

rootProject.name = "template-parent"

// Make sure to update bom/build.gradle.kts when making changes to modules.

sequenceOf(
    "bom",
    "common",
    "paper",
    "velocity"
).forEach {
    include("template-$it")
    project(":template-$it").projectDir = file(it)
}
