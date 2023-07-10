plugins {
    id("template.common-conventions")
    id("template.velocity-conventions")
}

dependencies {
    api(projects.templateCommon)
    compileOnly(libs.velocity)
    annotationProcessor(libs.velocity)
    compileOnly(libs.bstats.velocity)
}

applyJarMetadata("net.deltapvp.template.velocity")
