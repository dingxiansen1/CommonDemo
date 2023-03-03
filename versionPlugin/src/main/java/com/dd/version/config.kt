package com.dd.version

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

object Version {
    const val compose_version = "1.4.1"

    /**
     *  通用的添加进一个集合里
     **/
    val ComposeLibrary = arrayListOf<String>().apply {
        add(Compose.ui)
        add(Compose.material3)
        add(Compose.foundation)
        add(Compose.animation)
        add(Compose.activity)
        add(Compose.ui_tooling_preview)
        add(Compose.icon)
        //compose 路由导航库
        add(Compose.navigation)
    }



    val AppLibrary = arrayListOf<String>().apply {
        add(AndroidX.core)
        add(AndroidX.appcompat)
        //kotlin 协程
        add(Kotlin.core)
        add(Kotlin.android)
        // splashscreen 启动页
        add(AndroidX.splashscreen)
        //lifecycle
        add(Jetpack.lifecycle_runtime)
        add(Jetpack.lifecycle_runtime_compose)
        add(Jetpack.lifecycle_viewmodel)
        add(Jetpack.lifecycle_viewmodel_compose)
        add(Jetpack.lifecycle_livedata)

        add(Jetpack.datastore_core)
        add(Jetpack.datastore_preferences)
        //App Startup 启动优化
        add(Jetpack.startup)
        //paging3
        add(Jetpack.paging)
        add(Jetpack.paging_compose)

        //coil图片加载
        add(Other.coil)
        add(Other.coil_compose)
        add(Other.coil_gif)
    }
}

object Maven {
    const val jitpackIOUrl = "https://jitpack.io"
}

object GradlePlugins {
    const val androidApplication = "com.android.application"
    const val android = "'org.jetbrains.kotlin.android'"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinParcelize = "kotlin-parcelize"
    const val kotlinKapt = "kotlin-kapt"
    const val javaLib = "java-library"
    const val androidLib = "com.android.library"
    const val navigationSafeArgs = "androidx.navigation.safeargs"
}

object Android {
    const val minSdk = 21
    const val compileSdk = 33
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"
}

object AndroidX {
    const val core = "androidx.core:core-ktx:1.9.0"
    const val appcompat = "androidx.appcompat:appcompat:1.6.0"
    const val splashscreen = "androidx.core:core-splashscreen:1.0.0"
}

object AndroidTest {
    const val junit = "junit:junit:4.13.2"
    const val ext = "androidx.test.ext:junit:1.1.3"
    const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val compose = "androidx.compose.ui:ui-test-junit4:1.3.3"
}

object Kotlin {
    const val kotlinJvmTarget = "1.8"
    private const val kotlin_version = "1.6.4"
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_version"
}

object Jetpack {
    private const val lifecycle_version = "2.6.0-alpha05"
    private const val datastore_version = "1.0.0"
    private const val hilt_version = "2.44"
    private const val room_version = "2.4.2"
    const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    const val lifecycle_runtime_compose =
        "androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version"
    const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    const val lifecycle_viewmodel_compose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version"
    const val datastore_preferences = "androidx.datastore:datastore-preferences:$datastore_version"
    const val datastore_core = "androidx.datastore:datastore-core:$datastore_version"
    const val startup = "androidx.startup:startup-runtime:1.2.0-alpha01"

    const val hilt = "com.google.dagger:hilt-android:$hilt_version"
    const val hilt_kapt = "com.google.dagger:hilt-android-compiler:$hilt_version"
    const val hilt_navigation = "androidx.hilt:hilt-navigation-compose:1.1.0-alpha01"


    const val paging = "androidx.paging:paging-runtime:3.0.1"
    const val paging_compose = "androidx.paging:paging-compose:1.0.0-alpha12"
    const val room = "androidx.room:room-ktx:$room_version"
    const val room_runtime = "androidx.room:room-runtime:$room_version"
    const val room_compiler = "androidx.room:room-compiler:$room_version"
}

object Compose {
    const val activity = "androidx.activity:activity-compose"
    const val ui = "androidx.compose.ui:ui"
    const val material = "androidx.compose.material:material"
    const val material3 = "androidx.compose.material3:material3"
    const val foundation = "androidx.compose.foundation:foundation"
    const val animation = "androidx.compose.animation:animation"//动画


    const val ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview"
    const val ui_tooling = "androidx.compose.ui:ui-tooling"
    const val ui_test_manifest = "androidx.compose.ui:ui-test-manifest"


    const val navigation = "androidx.navigation:navigation-compose:2.6.0-alpha01"
    const val icon = "androidx.compose.material:material-icons-extended"


    private const val accompanist_version = "0.28.0"
    const val accompanist_insets =
        "com.google.accompanist:accompanist-insets:${accompanist_version}"
    const val accompanist_systemuicontroller =
        "com.google.accompanist:accompanist-systemuicontroller:${accompanist_version}"
    const val accompanist_coil = "com.google.accompanist:accompanist-coil:${accompanist_version}"
    const val accompanist_viewpager =
        "com.google.accompanist:accompanist-pager:${accompanist_version}"
    const val accompanist_viewpager_indicators =
        "com.google.accompanist:accompanist-pager-indicators:${accompanist_version}"
    const val accompanist_flowlayout =
        "com.google.accompanist:accompanist-flowlayout:${accompanist_version}"
    const val accompanist_web = "com.google.accompanist:accompanist-webview:${accompanist_version}"
}

object Other {
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.9.1"

    private const val coil_version = "2.1.0"
    const val coil = "io.coil-kt:coil:$coil_version"
    const val coil_compose = "io.coil-kt:coil-compose:$coil_version"
    const val coil_gif = "io.coil-kt:coil-gif:$coil_version"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:4.10.0"

    private const val retrofit2_version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit2_version"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:$retrofit2_version"

    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"

}