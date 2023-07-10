import org.gradle.kotlin.dsl.support.uppercaseFirstChar

plugins {
    id("template.common-conventions")
    id("template.paper-conventions")
}

dependencies {
    api(projects.templateCommon)
    compileOnly(libs.paper)
    implementation(libs.papertrail)
    implementation(libs.bstats.bukkit)
}

applyJarMetadata("net.deltapvp.template.paper")
