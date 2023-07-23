package net.deltapvp.template.velocity;

import java.util.Locale;

class ProjectData {

    public static final String ARTIFACT_ID = "${project.name.toLowerCase()}";
    public static final String ID = "${pluginId}";
    public static final String NAME = "${pluginName}";
    public static final String VERSION = "${project.version}";
    public static final String DESCRIPTION = "${project.description}";
    public static final String AUTHOR = "${pluginAuthor}";
}
