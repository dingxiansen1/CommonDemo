package com.dd.basiccompose.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController


val LocalNavController = compositionLocalOf<NavHostController> {
    error("Not Init")
}

fun NavHostController.go(
    url: String,
    isLaunchSingleTop: Boolean = true,
    needToRestoreState: Boolean = true,
) {
    this.navigate(url) {
        //避免重建
        launchSingleTop = isLaunchSingleTop
        //选择
        restoreState = needToRestoreState
    }
}