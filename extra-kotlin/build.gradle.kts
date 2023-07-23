plugins {
    id("template.kotlin-conventions")
}

dependencies {
    api(projects.templateCommon)
    compileOnly(libs.kotlin.stdlib)
}

applyJarMetadata("net.deltapvp.template.extra-kotlin")
