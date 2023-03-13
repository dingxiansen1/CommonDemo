import com.dd.version.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.dd.application")
}


android {
    namespace = "com.dd.basiccompose"
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Kotlin.kotlinJvmTarget
    }
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

    //BOM自动确认compose依赖的版本号
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    implementation(com.dd.version.Library.composeLibrary)

    testImplementation(AndroidTest.junit)
    androidTestImplementation(AndroidTest.ext)
    androidTestImplementation(AndroidTest.espresso)
    androidTestImplementation(AndroidTest.compose)
    debugImplementation(Compose.ui_tooling)

    implementation(project(":Utils"))
    implementation(project(":Common"))
    implementation(project(":Net"))

    //一个系统状态栏库
    implementation(Compose.accompanist_systemuicontroller)
    //viewPager
    implementation(Compose.accompanist_viewpager)
    implementation(Compose.accompanist_viewpager_indicators)
}