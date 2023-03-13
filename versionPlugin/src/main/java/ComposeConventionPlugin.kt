import com.dd.version.AndroidTest
import com.dd.version.Compose
import com.dd.version.Library
import com.dd.version.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {

                add("implementation", platform(Compose.bom))
                implementation(Library.composeLibrary)
                add("androidTestImplementation", AndroidTest.compose)
                add("debugImplementation", Compose.ui_tooling)
            }
        }
    }

}