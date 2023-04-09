package com.dd.basiccompose.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


object MidnightduskColor {
    object Light {
        private val primary = Color(0xFFBB0054)
        private val onPrimary = Color(0xFFFFFFFF)
        private val primaryContainer = Color(0xFFFFD9E1)
        private val onPrimaryContainer = Color(0xFF3F0017)
        private val secondary = Color(0xFFBB0054)
        private val onSecondary = Color(0xFFFFFFFF)
        private val secondaryContainer = Color(0xFFFFD9E1)
        private val onSecondaryContainer = Color(0xFF3F0017)
        private val tertiary = Color(0xFF006638)
        private val onTertiary = Color(0xFFFFFFFF)
        private val tertiaryContainer = Color(0xFF00894b)
        private val onTertiaryContainer = Color(0xFF2D1600)
        private val background = Color(0xFFFFFBFF)
        private val onBackground = Color(0xFF1C1B1F)
        private val surface = Color(0xFFFFFBFF)
        private val onSurface = Color(0xFF1C1B1F)
        private val surfaceVariant = Color(0xFFF3DDE0)
        private val onSurfaceVariant = Color(0xFF524346)
        private val outline = Color(0xFF847376)
        private val inverseOnSurface = Color(0xFFF4F0F4)
        private val inverseSurface = Color(0xFF313033)
        private val primaryInverse = Color(0xFFFFB1C4)
        val elevationOverlay = primary

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
        private val primary = Color(0xFFF02475)
        private val onPrimary = Color(0xFFFFFFFF)
        private val primaryContainer = Color(0xFFBD1C5C)
        private val onPrimaryContainer = Color(0xFFFFFFFF)
        private val secondary = Color(0xFFF02475)
        private val onSecondary = Color(0xFFFFFFFF)
        private val secondaryContainer = Color(0xFFF02475)
        private val onSecondaryContainer = Color(0xFFFFFFFF)
        private val tertiary = Color(0xFF55971C)
        private val onTertiary = Color(0xFFFFFFFF)
        private val tertiaryContainer = Color(0xFF386412)
        private val onTertiaryContainer = Color(0xFFE5E1E5)
        private val background = Color(0xFF16151D)
        private val onBackground = Color(0xFFE5E1E5)
        private val surface = Color(0xFF16151D)
        private val onSurface = Color(0xFFE5E1E5)
        private val surfaceVariant = Color(0xFF524346)
        private val onSurfaceVariant = Color(0xFFD6C1C4)
        private val outline = Color(0xFF9F8C8F)
        private val inverseSurface = Color(0xFF333043)
        private val inverseOnSurface = Color(0xFFFFFFFF)
        private val primaryInverse = Color(0xFFF02475)
        val elevationOverlay = Color(0xFF2C0013)

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



