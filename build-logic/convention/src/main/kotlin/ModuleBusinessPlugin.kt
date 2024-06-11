import org.gradle.api.Plugin
import org.gradle.api.Project

class ModuleBusinessPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        ModulePlugin().apply(project = project)
        with(project) {
            dependencies.apply {
                add("api", project(":module-base"))
            }
        }
    }

}