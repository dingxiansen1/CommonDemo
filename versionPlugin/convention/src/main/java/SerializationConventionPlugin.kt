import com.dd.version.Other
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SerializationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            //添加插件
            with(pluginManager) {
                apply("kotlinx-serialization")
            }
            dependencies {
                add("implementation", Other.serialization)
            }
        }
    }

}