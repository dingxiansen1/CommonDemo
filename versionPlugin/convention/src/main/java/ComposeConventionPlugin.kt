import com.dd.version.*
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

                //一个提供易于使用的实用程序的库，用于从 Jetpack Compose 重新着色 Android 系统条。
                add("implementation", Compose.accompanist_systemuicontroller)
                add("implementation", Compose.accompanist_web)
                add("implementation", Compose.accompanist_permissions)
                add("implementation",Jetpack.paging)
                add("implementation",Jetpack.paging_compose)
            }
        }
    }

}