plugins {
    id("xyz.jpenilla.run-paper")
    id("template.common-conventions")
}

tasks {
    runServer {
        minecraftVersion("1.20.1")

        jvmArguments.add("-Dcom.mojang.eula.agree=true")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)
    }

    named("clean", Delete::class) {
       delete(project.projectDir.resolve("run"))
   }

    if (plugins.hasPlugin("io.papermc.paperweight.userdev")) {
        named("assemble") {
            dependsOn(named("reobfJar"))
        }
    }
}