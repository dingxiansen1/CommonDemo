package com.dd.basiccompose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Purple80, secondary = PurpleGrey80, tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40, secondary = PurpleGrey40, tertiary = Pink40

)

@Composable
fun AppThemeDefault(
    content: @Composable () -> Unit
) {
    val mAppTheme = ThemeUtils.mAppTheme.collectAsState().value
    val colorScheme = when {
        mAppTheme.mIsFollowTheSystem -> {
            if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
        }
        mAppTheme.mIsNightModel -> DarkColorScheme
        else -> LightColorScheme
    }
    val uiSystem = rememberSystemUiController()
    uiSystem.setStatusBarColor(colorScheme.background)


    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}