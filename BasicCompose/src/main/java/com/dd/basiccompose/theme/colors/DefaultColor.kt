package com.dd.basiccompose.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object DefaultColor {

    object Light {
        private val primary = Color(0xFF0057CE)
        private val onPrimary = Color(0xFFFFFFFF)
        private val primaryContainer = Color(0xFFD8E2FF)
        private val onPrimaryContainer = Color(0xFF001947)
        private val secondary = Color(0xFF0057CE)
        private val onSecondary = Color(0xFFFFFFFF)
        private val secondaryContainer = Color(0xFFD8E2FF)
        private val onSecondaryContainer = Color(0xFF001947)
        private val tertiary = Color(0xFF006E17)
        private val onTertiary = Color(0xFFFFFFFF)
        private val tertiaryContainer = Color(0xFF95F990)
        private val onTertiaryContainer = Color(0xFF002202)
        private val background = Color(0xFFFDFBFF)
        private val onBackground = Color(0xFF1B1B1E)
        private val surface = Color(0xFFFDFBFF)
        private val onSurface = Color(0xFF1B1B1E)
        private val surfaceVariant = Color(0xFFE1E2EC)
        private val onSurfaceVariant = Color(0xFF44464E)
        private val outline = Color(0xFF757780)
        private val inverseOnSurface = Color(0xFFF2F0F4)
        private val inverseSurface = Color(0xFF303033)
        val primaryInverse = Color(0xFFAEC6FF)

        val colorScheme = lightColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            tertiary = tertiary,
            onTertiary = onTertiary,
            tertiaryContainer = tertiaryContainer,
            onTertiaryContainer = onTertiaryContainer,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            outline = outline,
            inverseOnSurface = inverseOnSurface,
            inverseSurface = inverseSurface,
            inversePrimary = primaryInverse,
        )
    }

    object Dark {
        private val primary = Color(0xFFAEC6FF)
        private val onPrimary = Color(0xFF002C71)
        private val primaryContainer = Color(0xFF00419E)
        private val onPrimaryContainer = Color(0xFFD8E2FF)
        private val secondary = Color(0xFFAEC6FF)
        private val onSecondary = Color(0xFF002C71)
        private val secondaryContainer = Color(0xFF00419E)
        private val onSecondaryContainer = Color(0xFFD8E2FF)
        private val tertiary = Color(0xFF7ADC77)
        private val onTertiary = Color(0xFF003907)
        private val tertiaryContainer = Color(0xFF00530D)
        private val onTertiaryContainer = Color(0xFF95F990)
        private val background = Color(0xFF1B1B1E)
        private val onBackground = Color(0xFFE4E2E6)
        private val surface = Color(0xFF1B1B1E)
        private val onSurface = Color(0xFFE4E2E6)
        private val surfaceVariant = Color(0xFF44464E)
        private val onSurfaceVariant = Color(0xFFC5C6D0)
        private val outline = Color(0xFF8E9099)
        private val inverseOnSurface = Color(0xFF1B1B1E)
        private val inverseSurface = Color(0xFFE4E2E6)
        val primaryInverse = Color(0xFF0057CE)

        val colorScheme = darkColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            tertiary = tertiary,
            onTertiary = onTertiary,
            tertiaryContainer = tertiaryContainer,
            onTertiaryContainer = onTertiaryContainer,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            outline = outline,
            inverseOnSurface = inverseOnSurface,
            inverseSurface = inverseSurface,
            inversePrimary = primaryInverse,
        )
    }

}