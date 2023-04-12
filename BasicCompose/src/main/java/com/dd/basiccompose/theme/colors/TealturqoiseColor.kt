package com.dd.basiccompose.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


object TealturqoiseColor {

    object Light {
        private val primary = Color(0xFF008080)
        private val onPrimary = Color(0xFFFFFFFF)
        private val primaryContainer = Color(0xFF76B1B1)
        private val onPrimaryContainer = Color(0xFFFFFFFF)
        private val secondary = Color(0xFF008080)
        private val onSecondary = Color(0xFFFFFFFF)
        private val secondaryContainer = Color(0xFFBFDFDF)
        private val onSecondaryContainer = Color(0xFF008080)
        private val tertiary = Color(0xFFFF7F7F)
        private val onTertiary = Color(0xFF000000)
        private val tertiaryContainer = Color(0xFF2A1616)
        private val onTertiaryContainer = Color(0xFFFF7F7F)
        private val background = Color(0xFFFAFAFA)
        private val onBackground = Color(0xFF050505)
        private val surface = Color(0xFFFAFAFA)
        private val onSurface = Color(0xFF050505)
        private val surfaceVariant = Color(0xFFDAE5E2)
        private val onSurfaceVariant = Color(0xFF050505)
        private val outline = Color(0xFF6F7977)
        private val inverseOnSurface = Color(0xFFFAFAFA)
        private val inverseSurface = Color(0xFF050505)
        private val primaryInverse = Color(0xFF40E0D0)
        val elevationOverlay = Color(0xFFBFDFDF)

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
        private val primary = Color(0xFF40E0D0)
        private val onPrimary = Color(0xFF000000)
        private val primaryContainer = Color(0xFF40E0D0)
        private val onPrimaryContainer = Color(0xFF000000)
        private val secondary = Color(0xFF40E0D0)
        private val onSecondary = Color(0xFF000000)
        private val secondaryContainer = Color(0xFF18544E)
        private val onSecondaryContainer = Color(0xFF40E0D0)
        private val tertiary = Color(0xFFBF1F2F)
        private val onTertiary = Color(0xFFFFFFFF)
        private val tertiaryContainer = Color(0xFF200508)
        private val onTertiaryContainer = Color(0xFFBF1F2F)
        private val background = Color(0xFF202125)
        private val onBackground = Color(0xFFDFDEDA)
        private val surface = Color(0xFF202125)
        private val onSurface = Color(0xFFDFDEDA)
        private val surfaceVariant = Color(0xFF3F4947)
        private val onSurfaceVariant = Color(0xFFDFDEDA)
        private val outline = Color(0xFF899391)
        private val inverseOnSurface = Color(0xFF202125)
        private val inverseSurface = Color(0xFFDFDEDA)
        private val primaryInverse = Color(0xFF008080)
        val elevationOverlay = Color(0xFF18544E)

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