import com.dd.version.*

plugins {
    id("kotlinx-serialization")
    //通过自定义插件进行版本控制
    id("com.dd.app")
    id("com.dd.common")
    id("com.dd.compose")
    id("com.dd.test")
}

android {
    namespace = "com.dd.readx"
    compileSdk = Android.compileSdk


    defaultConfig {
        applicationId = "com.dd.readx"
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(com.dd.version.Library.netLibrary)

    implementation(project(":BasicCompose"))
    implementation(project(":Common"))
    implementation(project(":Utils"))
    implementation(project(":Net"))

    //一个提供易于使用的实用程序的库，用于从 Jetpack Compose 重新着色 Android 系统条。
    implementation(Compose.accompanist_systemuicontroller)
    //viewPager
    implementation(Compose.accompanist_viewpager)
    implementation(Compose.accompanist_viewpager_indicators)

    implementation(Compose.accompanist_flowlayout)

    implementation(Other.serialization)
    //paging
    implementation(Jetpack.paging)
    implementation(Jetpack.paging_compose)

}