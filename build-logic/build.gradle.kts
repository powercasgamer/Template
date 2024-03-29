plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(files(libs::class.java.protectionDomain.codeSource.location))
    implementation(libs.minotaur)
    implementation(libs.templating)
    implementation(libs.hangar.publish)
    implementation(libs.indra.common)
    implementation(libs.indra.git)
    implementation(libs.indra.spotless)
    implementation(libs.indra.crossdoc)
    implementation(libs.shadow)
    implementation(libs.paperweight)
    implementation(libs.run.task)
    implementation(libs.kotlin.gradle)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    target {
        compilations.configureEach {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
}