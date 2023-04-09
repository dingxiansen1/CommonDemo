package com.dd.version

object Library {


    /**
     *  通用的添加进一个集合里
     **/
    val composeLibrary = arrayListOf<String>().apply {
        add(Compose.ui)
        add(Compose.material3)
        add(Compose.foundation)
        add(Compose.animation)
        add(Compose.activity)
        add(Compose.ui_tooling_preview)
        add(Compose.icon)
        // add(Compose.text)
        //compose 路由导航库
        add(Compose.navigation)
    }
    val lifecycleLibrary = arrayListOf<String>().apply {
        add(Jetpack.lifecycle_runtime)
        add(Jetpack.lifecycle_runtime_compose)
        add(Jetpack.lifecycle_viewmodel)
        add(Jetpack.lifecycle_viewmodel_compose)
        add(Jetpack.lifecycle_livedata)
    }

    val coilLibrary = arrayListOf<String>().apply {
        add(Other.coil)
        add(Other.coil_compose)
        add(Other.coil_gif)
    }

    val netLibrary = arrayListOf<String>().apply {
        add(Other.okhttp3)
        add(Other.retrofit)
        add(Other.retrofit_gson)
        add(Other.jsoup)
    }

    //kotlin 协程
    val coroutineLibrary = arrayListOf<String>().apply {
        //kotlin 协程
        add(Kotlin.core)
        add(Kotlin.android)
    }

    //coroutineLibrary  lifecycleLibrary  coilLibrary
    // 等需要放在appLibrary前面
    //否则未初始化直接addAll会空指针
    val appLibrary = arrayListOf<String>().apply {
        add(AndroidX.core)
        add(AndroidX.appcompat)
        add(AndroidX.androidPaletteKtx)
        //kotlin 协程
        addAll(coroutineLibrary)

        // splashscreen 启动页
        add(AndroidX.splashscreen)
        //lifecycle
        addAll(lifecycleLibrary)

        add(Other.serialization)

        add(Jetpack.datastore_core)
        add(Jetpack.datastore_preferences)
        //App Startup 启动优化
        add(Jetpack.startup)
        //paging3
        add(Jetpack.paging)
        add(Jetpack.paging_compose)

        //coil图片加载
        addAll(coilLibrary)

        add(Jetpack.work)
        add(Jetpack.work_multiprocess)
    }

}