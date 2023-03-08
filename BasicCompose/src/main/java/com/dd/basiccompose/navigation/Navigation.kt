package com.dd.basiccompose.navigation

import androidx.navigation.NavHostController


fun NavHostController.go(
    url: String,
    isLaunchSingleTop: Boolean = false,
    needToRestoreState: Boolean = true,
) {
    this.navigate(url) {
        //避免重建
        launchSingleTop = isLaunchSingleTop
        //选择
        restoreState = needToRestoreState
    }
}