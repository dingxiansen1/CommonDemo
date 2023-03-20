import com.dd.version.AndroidTest
import com.dd.version.Compose
import com.dd.version.Jetpack
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                add("testImplementation", AndroidTest.junit)
                add("androidTestImplementation", AndroidTest.ext)
                add("androidTestImplementation", AndroidTest.espresso)
            }
        }
    }

}