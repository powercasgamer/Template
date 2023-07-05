plugins {
    id("java-platform")
    id("template.base-conventions")
}

indra {
    configurePublications {
        from(components["javaPlatform"])
    }
}

dependencies {
    constraints {
        sequenceOf(
            "common",
            "paper",
            "velocity",
        ).forEach {
            api(project(":template-$it"))
        }
    }
}

applyJarMetadata("net.deltapvp.template.bom")