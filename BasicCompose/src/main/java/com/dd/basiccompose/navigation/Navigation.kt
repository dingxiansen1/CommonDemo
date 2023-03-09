package com.dd.basiccompose.navigation

import androidx.navigation.NavHostController


fun NavHostController.go(
    url: String,
    isLaunchSingleTop: Boolean = false,
    needToRestoreState: Boolean = true,
    popToUp: String? = null,
    popToUpInclusive: Boolean = true,
) {
    this.navigate(url) {
        //是否弹出路由栈
        popToUp?.let {
            popUpTo(it) {
                inclusive = popToUpInclusive
            }
        }
        //避免重建
        launchSingleTop = isLaunchSingleTop
        //选择
        restoreState = needToRestoreState
    }
}