plugins {
    id("template.common-conventions")
}

dependencies {
    compileOnly(libs.annotations)
}

applyJarMetadata("net.deltapvp.template.common")
