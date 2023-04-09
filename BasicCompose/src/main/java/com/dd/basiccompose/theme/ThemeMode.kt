package com.dd.basiccompose.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.dd.basiccompose.R
import com.dd.basiccompose.theme.colors.*

fun ThemeMode.getColorScheme(isDark: Boolean): ColorScheme {
    return if (isDark) darkColorScheme else lightColorScheme
}

enum class ThemeMode(
    val darkColorScheme: ColorScheme,
    val lightColorScheme: ColorScheme,
    val titleResId: Int,
    val darkElevationOverlay: Color = Color.Unspecified,
    val lightElevationOverlay: Color = Color.Unspecified,
) {

    Default(
        DefaultColor.Dark.colorScheme,
        DefaultColor.Light.colorScheme,
        R.string.theme_default
    ),

    Greenapple(
        GreenappleColor.Dark.colorScheme,
        GreenappleColor.Light.colorScheme,
        R.string.theme_greenapple
    ),

    Midnightdusk(
        MidnightduskColor.Dark.colorScheme,
        MidnightduskColor.Light.colorScheme,
        R.string.theme_midnightdusk,
        MidnightduskColor.Dark.elevationOverlay,
        MidnightduskColor.Light.elevationOverlay,
    ),

    Strawberry(
        StrawberryColor.Dark.colorScheme,
        StrawberryColor.Light.colorScheme,
        R.string.theme_strawberry,
    ),



    Tako(
        TakoColor.Dark.colorScheme,
        TakoColor.Light.colorScheme,
        R.string.theme_tako,
        TakoColor.Dark.elevationOverlay,
        TakoColor.Light.elevationOverlay
    ),

    Tealturqoise(
        TealturqoiseColor.Dark.colorScheme,
        TealturqoiseColor.Light.colorScheme,
        R.string.theme_tealturqoise,
        TealturqoiseColor.Dark.elevationOverlay,
        TealturqoiseColor.Light.elevationOverlay
    )

}