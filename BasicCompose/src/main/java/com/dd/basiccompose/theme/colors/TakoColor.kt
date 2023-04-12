package com.dd.basiccompose.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object TakoColor {

    object Light {
        private val primary = Color(0xFF66577E)
        private val onPrimary = Color(0xFFF3B375)
        private val primaryContainer = Color(0xFF8D80A2)
        private val onPrimaryContainer = Color(0xFFF3B375)
        private val secondary = Color(0xFF66577E)
        private val onSecondary = Color(0xFFF3B375)
        private val secondaryContainer = Color(0xFF66577E)
        private val onSecondaryContainer = Color(0xFFF3B375)
        private val tertiary = Color(0xFFF3B375)
        private val onTertiary = Color(0xFF574360)
        private val tertiaryContainer = Color(0xFFFDD6B0)
        private val onTertiaryContainer = Color(0xFF221437)
        private val background = Color(0xFFF7F5FF)
        private val onBackground = Color(0xFF1B1B22)
        private val surface = Color(0xFFF7F5FF)
        private val onSurface = Color(0xFF1B1B22)
        private val surfaceVariant = Color(0xFFE8E0EB)
        private val onSurfaceVariant = Color(0xFF49454E)
        private val outline = Color(0xFF7A757E)
        private val inverseOnSurface = Color(0xFFF3EFF4)
        private val inverseSurface = Color(0xFF313033)
        private val primaryInverse = Color(0xFFD6BAFF)
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
        private val primary = Color(0xFFF3B375)
        private val onPrimary = Color(0xFF38294E)
        private val primaryContainer = Color(0xFFF3B375)
        private val onPrimaryContainer = Color(0xFF38294E)
        private val secondary = Color(0xFFF3B375)
        private val onSecondary = Color(0xFF38294E)
        private val secondaryContainer = Color(0xFFF3B375)
        private val onSecondaryContainer = Color(0xFF38294E)
        private val tertiary = Color(0xFF66577E)
        private val onTertiary = Color(0xFFF3B375)
        private val tertiaryContainer = Color(0xFF4E4065)
        private val onTertiaryContainer = Color(0xFFEDDCFF)
        private val background = Color(0xFF21212E)
        private val onBackground = Color(0xFFE3E0F2)
        private val surface = Color(0xFF21212E)
        private val onSurface = Color(0xFFE3E0F2)
        private val surfaceVariant = Color(0xFF49454E)
        private val onSurfaceVariant = Color(0xFFCBC4CE)
        private val outline = Color(0xFF958F99)
        private val inverseOnSurface = Color(0xFF1B1B1E)
        private val inverseSurface = Color(0xFFE5E1E6)
        private val primaryInverse = Color(0xFF84531E)
        val elevationOverlay = primary

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