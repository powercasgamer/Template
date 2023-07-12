import org.gradle.kotlin.dsl.support.uppercaseFirstChar

plugins {
    id("template.common-conventions")
    id("template.paper-conventions")
    id("io.papermc.paperweight.userdev")
}

dependencies {
    api(projects.templateCommon)
    // compileOnly(libs.paper)
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
        implementation(libs.papertrail)
    implementation(libs.bstats.bukkit)
}

applyJarMetadata("net.deltapvp.template.paper")

tasks {
    // Configure reobfJar to run when invoking the build task
    assemble {
      dependsOn(reobfJar)
    }
}
