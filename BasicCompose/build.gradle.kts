import com.dd.version.*

plugins {
    id("com.dd.library")
    id("com.dd.common")
    id("com.dd.compose")
    id("com.dd.test")
}


android {

    namespace = "com.dd.basiccompose"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":Common:Utils"))
    implementation(project(":Common:Common"))
    implementation(project(":Common:Net"))

    //一个系统状态栏库
    implementation(Compose.accompanist_systemuicontroller)
    //viewPager
    implementation(Compose.accompanist_viewpager_indicators)

    implementation(Compose.accompanist_web)

}