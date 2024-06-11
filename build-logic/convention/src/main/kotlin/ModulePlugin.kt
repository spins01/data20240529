import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        LibPlugin().apply(project = project)
        ComponentKspConfigPlugin().apply(project = project)
    }

}