package com.dd.utils

import android.content.pm.PackageManager
import androidx.annotation.Keep

object ApplicationUtils {

    val appInfo by lazy {
        val appInfo = AppInfo()
        @Suppress("DEPRECATION")
        Utils.getApp().run {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
                ?.let {
                    appInfo.versionName = it.versionName
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                        appInfo.versionCode = it.longVersionCode.toString()
                    } else {
                        @Suppress("DEPRECATION")
                        appInfo.versionCode = it.versionCode.toString()
                    }
                }
        }
        appInfo
    }
}

@Keep
data class AppInfo(
    var versionCode: String = "",
    var versionName: String = ""
)