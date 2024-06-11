import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

open class ComponentKspConfigPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.apply {
                apply("com.google.devtools.ksp")
            }
            extensions.configure<KspExtension> {
                var tempProject: Project = project
                var name = tempProject.name
                while (tempProject.parent != null) {
                    tempProject = tempProject.parent!!
                    name = tempProject.name + "_" + name
                }
                arg("ModuleName", name)
            }
            dependencies.apply {
                add("ksp", libs.findLibrary("kcomponent-compiler").get().get().toString())
                add("ksp", libs.findLibrary("room_compiler").get().get().toString())
            }
        }
    }

}