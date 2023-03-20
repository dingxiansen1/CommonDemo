import org.gradle.api.Plugin
import org.gradle.api.Project

class AppConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            //添加插件
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-kapt")
            }
        }
    }

}