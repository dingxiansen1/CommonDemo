package com.dd.basiccompose.theme

import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dd.basiccompose.controller.LocalNavController
import com.dd.basiccompose.controller.LocalSystemUiController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun NormalSystemBarColor(
    getStatusBarDark: (Boolean) -> Boolean = { !it },
    getNavigationBarDark: (Boolean) -> Boolean = { !it },
    uiController: SystemUiController = rememberSystemUiController(),
) {
    val isDark = ThemeUtils.isDarkMode()
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect() {
            uiController.setStatusBarColor(Color.Transparent, getStatusBarDark(isDark))
            uiController.setNavigationBarColor(Color.Transparent, getNavigationBarDark(isDark))
        }
    }
}


@Composable
fun DefaultTheme(
    content: @Composable () -> Unit
) {
    //导航
    val navController = rememberNavController()
    //系统状态栏
    val uiSystem = rememberSystemUiController()

    //隐式参数 LocalNavController ,避免全局传递
    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalSystemUiController provides uiSystem,
    ) {
        val appTheme by ThemeUtils.appTheme.collectAsStateWithLifecycle()

        val isDynamic = appTheme.isDynamicColor && ThemeUtils.isSupportDynamicColor()
        val isDark = when (appTheme.darkMode) {
            DarkMode.Dark -> true
            DarkMode.Light -> false
            else -> isSystemInDarkTheme()
        }


        val colorScheme = when {
            isDynamic && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)

            }

            else -> {
                Log.d("EasyTheme", appTheme.themeMode.name)
                appTheme.themeMode.getColorScheme(isDark)
            }
        }

        uiSystem.setSystemBarsColor(colorScheme.background)
        uiSystem.isSystemBarsVisible = true
        MaterialTheme(
            colorScheme = colorScheme, typography = Typography, content = content
        )
    }
}