package com.dd.basiccompose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Purple80, secondary = PurpleGrey80, tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40, secondary = PurpleGrey40, tertiary = Pink40

)

@Composable
fun DefaultTheme(
    content: @Composable () -> Unit
) {
    val appTheme by ThemeUtils.mAppTheme.collectAsStateWithLifecycle()
    val colorScheme = when {
        appTheme.mIsFollowTheSystem -> {
            if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
        }
        appTheme.mIsNightModel -> DarkColorScheme
        else -> LightColorScheme
    }
    val uiSystem = rememberSystemUiController()
    uiSystem.setStatusBarColor(colorScheme.background)


    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}