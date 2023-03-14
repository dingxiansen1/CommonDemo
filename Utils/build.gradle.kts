import com.dd.version.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.dd.application")
    id("com.dd.test")
}


android {
    namespace = "com.dd.utils"
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Kotlin.kotlinJvmTarget
    }
}

dependencies {
    implementation(AndroidX.core)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.androidPaletteKtx)
}