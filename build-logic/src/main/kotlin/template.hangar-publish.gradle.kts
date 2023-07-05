import io.papermc.hangarpublishplugin.model.Platforms

plugins {
    id("template.common-conventions")
    id("io.papermc.hangar-publish-plugin")
}

hangarPublish {
    publications.register("plugin") {
//        this.apiEndpoint.set("https://hangar.papermc.dev/api/v1/")
        version.set(project.versionString())
        namespace("powercas_gamer", providers.gradleProperty("projectName").getOrElse("Template"))
        channel.set(rootProject.channel())
        platforms {
            if (project.name.endsWith("paper", ignoreCase = true)) {
                register(Platforms.PAPER) {
                    jar.set(tasks.shadowJar.flatMap { it.archiveFile })
                    platformVersions.set(listOf("1.20", "1.20.1"))
                    this.dependencies {
                    }
                }
            } else if (project.name.endsWith("velocity", ignoreCase = true)) {
                register(Platforms.VELOCITY) {
                    jar.set(tasks.shadowJar.flatMap { it.archiveFile })
                    platformVersions.set(listOf("3.1.2"))
                    this.dependencies {
                    }
                }
            }
        }
        pages {
            resourcePage(provider { file("README.md").readText() })
        }
    }
}
