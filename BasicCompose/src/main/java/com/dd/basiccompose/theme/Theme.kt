package com.dd.basiccompose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dd.basiccompose.controller.LocalNavController
import com.dd.basiccompose.controller.LocalSystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(

)

private val LightColorScheme = lightColorScheme(

)

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
        val appTheme by ThemeUtils.mAppTheme.collectAsStateWithLifecycle()
        val colorScheme = when {
            appTheme.mIsFollowTheSystem -> {
                if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
            }
            appTheme.mIsNightModel -> DarkColorScheme
            else -> LightColorScheme
        }
        uiSystem.setSystemBarsColor(colorScheme.background)
        MaterialTheme(
            colorScheme = colorScheme, typography = Typography, content = content
        )
    }
}