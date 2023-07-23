plugins {
    id("template.common-conventions")
    id("xyz.jpenilla.run-velocity")
    id("ca.stellardrift.templating")
}

tasks {
    runVelocity  {
        velocityVersion("3.2.0-SNAPSHOT")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)
    }

    generateTemplates {
        properties(mapOf(
            "pluginId" to providers.gradleProperty("projectName").getOrElse("template").lowercase(),
            "pluginName" to providers.gradleProperty("projectName").getOrElse("template"),
            "pluginAuthor" to providers.gradleProperty("projectAuthor").getOrElse("template"),
            "project" to project
        ))
    }

   named("clean", Delete::class) {
       delete(project.projectDir.resolve("run"))
   }
}