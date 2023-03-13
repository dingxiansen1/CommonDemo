import com.dd.version.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.dd.application")
    id("com.dd.compose")
    id("com.dd.test")
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

    implementation(project(":Utils"))
    implementation(project(":Common"))
    implementation(project(":Net"))

    //一个系统状态栏库
    implementation(Compose.accompanist_systemuicontroller)
    //viewPager
    implementation(Compose.accompanist_viewpager)
    implementation(Compose.accompanist_viewpager_indicators)
}