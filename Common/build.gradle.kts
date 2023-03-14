import com.dd.version.*
plugins {
    id("com.dd.library")
    id("com.dd.common")
}


android {
    namespace = "com.dd.common"
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

    testImplementation(AndroidTest.junit)
    androidTestImplementation(AndroidTest.ext)
    androidTestImplementation(AndroidTest.espresso)

    implementation(project(":Utils"))
    implementation(project(":Net"))

    //leakcanary
    debugImplementation(Other.leakcanary)
}