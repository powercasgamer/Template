import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("template.common-conventions")
    kotlin("jvm")
}

tasks {
    withType(KotlinCompile::class).configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(Constants.JAVA_VERSION.toString()))
            languageVersion.set(KotlinVersion.KOTLIN_1_9)
        }
    }
}

kotlin {
    jvmToolchain(Constants.JAVA_VERSION)
}
