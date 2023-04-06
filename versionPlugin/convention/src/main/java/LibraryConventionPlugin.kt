import com.android.build.gradle.LibraryExtension
import com.dd.version.Android
import com.dd.version.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            //添加插件
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-kapt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk = Android.targetSdk
                configureKotlinAndroid(this)
            }
        }
    }

}