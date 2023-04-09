package com.dd.basiccompose.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object GreenappleColor {
    object Light {
        private val primary = Color(0xFF006D2F)
        private val onPrimary = Color(0xFFFFFFFF)
        private val primaryContainer = Color(0xFF96F8A9)
        private val onPrimaryContainer = Color(0xFF002109)
        private val secondary = Color(0xFF006D2F)
        private val onSecondary = Color(0xFFFFFFFF)
        private val secondaryContainer = Color(0xFF96F8A9)
        private val onSecondaryContainer = Color(0xFF002109)
        private val tertiary = Color(0xFFB91D22)
        private val onTertiary = Color(0xFFFFFFFF)
        private val tertiaryContainer = Color(0xFFFFDAD5)
        private val onTertiaryContainer = Color(0xFF410003)
        private val background = Color(0xFFFBFDF7)
        private val onBackground = Color(0xFF1A1C19)
        private val surface = Color(0xFFFBFDF7)
        private val onSurface = Color(0xFF1A1C19)
        private val surfaceVariant = Color(0xFFDDE5DA)
        private val onSurfaceVariant = Color(0xFF414941)
        private val outline = Color(0xFF717970)
        private val inverseOnSurface = Color(0xFFF0F2EC)
        private val inverseSurface = Color(0xFF2F312E)
        val primaryInverse = Color(0xFF7ADB8F)

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
            inversePrimary = primaryInverse

        )
    }

    object Dark {
        private val primary = Color(0xFF7ADB8F)
        private val onPrimary = Color(0xFF003915)
        private val primaryContainer = Color(0xFF005322)
        private val onPrimaryContainer = Color(0xFF96F8A9)
        private val secondary = Color(0xFF7ADB8F)
        private val onSecondary = Color(0xFF003915)
        private val secondaryContainer = Color(0xFF005322)
        private val onSecondaryContainer = Color(0xFF96F8A9)
        private val tertiary = Color(0xFFFFB3AA)
        private val onTertiary = Color(0xFF680006)
        private val tertiaryContainer = Color(0xFF93000D)
        private val onTertiaryContainer = Color(0xFFFFDAD5)
        private val background = Color(0xFF1A1C19)
        private val onBackground = Color(0xFFE1E3DD)
        private val surface = Color(0xFF1A1C19)
        private val onSurface = Color(0xFFE1E3DD)
        private val surfaceVariant = Color(0xFF414941)
        private val onSurfaceVariant = Color(0xFFC1C8BE)
        private val outline = Color(0xFF8B9389)
        private val inverseOnSurface = Color(0xFF1A1C19)
        private val inverseSurface = Color(0xFFE1E3DD)
        val primaryInverse = Color(0xFF006D2F)

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
            inversePrimary = primaryInverse

        )
    }
}


