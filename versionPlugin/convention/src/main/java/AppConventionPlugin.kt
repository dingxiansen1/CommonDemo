import com.android.build.api.dsl.ApplicationExtension
import com.dd.version.Android
import com.dd.version.Jetpack
import com.dd.version.Other
import com.dd.version.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AppConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            //添加插件
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-kapt")
                apply("kotlinx-serialization")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig.targetSdk = Android.targetSdk
                configureKotlinAndroid(this)
            }
        }
    }

}