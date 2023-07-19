import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.papermc.paperweight.tasks.RemapJar

plugins {
    id("template.common-conventions")
    id("io.papermc.paperweight.userdev")
    id("xyz.jpenilla.run-paper")
}

tasks {
    runServer {
        minecraftVersion(Constants.MINECRAFT_VERSION)

        jvmArguments.add("-Dcom.mojang.eula.agree=true")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)
    }

    named("clean", Delete::class) {
       delete(project.projectDir.resolve("run"))
   }

    assemble {
        dependsOn(reobfJar)
    }

    reobfJar {
        outputJar.set(layout.buildDirectory.file("libs/${project.nameString()}-${project.versionString()}.jar"))
    }
}

configurations.paperweightDevelopmentBundle {
    resolutionStrategy.cacheChangingModulesFor(3, "days")
}