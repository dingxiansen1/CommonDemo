package com.dd.basiccompose.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


object StrawberryColor {
    object Light {
        private val primary = Color(0xFFB61E40)
        private val onPrimary = Color(0xFFFFFFFF)
        private val primaryContainer = Color(0xFFFFDADD)
        private val onPrimaryContainer = Color(0xFF40000D)
        private val secondary = Color(0xFFB61E40)
        private val onSecondary = Color(0xFFFFFFFF)
        private val secondaryContainer = Color(0xFFFFDADD)
        private val onSecondaryContainer = Color(0xFF40000D)
        private val tertiary = Color(0xFF775930)
        private val onTertiary = Color(0xFFFFFFFF)
        private val tertiaryContainer = Color(0xFFFFDDB1)
        private val onTertiaryContainer = Color(0xFF2A1800)
        private val background = Color(0xFFFCFCFC)
        private val onBackground = Color(0xFF201A1A)
        private val surface = Color(0xFFFCFCFC)
        private val onSurface = Color(0xFF201A1A)
        private val surfaceVariant = Color(0xFFF4DDDD)
        private val onSurfaceVariant = Color(0xFF534344)
        private val outline = Color(0xFF857374)
        private val inverseOnSurface = Color(0xFFFBEDED)
        private val inverseSurface = Color(0xFF362F2F)
        val primaryInverse = Color(0xFFFFB2B9)

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
        private val primary = Color(0xFFFFB2B9)
        private val onPrimary = Color(0xFF67001B)
        private val primaryContainer = Color(0xFF91002A)
        private val onPrimaryContainer = Color(0xFFFFDADD)
        private val secondary = Color(0xFFFFB2B9)
        private val onSecondary = Color(0xFF67001B)
        private val secondaryContainer = Color(0xFF91002A)
        private val onSecondaryContainer = Color(0xFFFFDADD)
        private val tertiary = Color(0xFFE8C08E)
        private val onTertiary = Color(0xFF432C06)
        private val tertiaryContainer = Color(0xFF5D421B)
        private val onTertiaryContainer = Color(0xFFFFDDB1)
        private val background = Color(0xFF201A1A)
        private val onBackground = Color(0xFFECDFDF)
        private val surface = Color(0xFF201A1A)
        private val onSurface = Color(0xFFECDFDF)
        private val surfaceVariant = Color(0xFF534344)
        private val onSurfaceVariant = Color(0xFFD7C1C2)
        private val outline = Color(0xFFA08C8D)
        private val inverseOnSurface = Color(0xFF201A1A)
        private val inverseSurface = Color(0xFFECDFDF)
        val primaryInverse = Color(0xFFB61E40)

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
