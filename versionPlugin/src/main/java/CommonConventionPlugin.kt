import com.dd.version.Library
import com.dd.version.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CommonConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
              implementation(Library.appLibrary)
            }
        }
    }

}