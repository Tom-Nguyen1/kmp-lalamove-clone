package com.lalamove.clone.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LalamoveLightColorScheme =
        lightColorScheme(
                primary = LalamoveOrange,
                onPrimary = NeutralWhite,
                primaryContainer = LalamoveOrangeSurface,
                onPrimaryContainer = LalamoveOrangeDark,
                secondary = NeutralGray800,
                onSecondary = NeutralWhite,
                secondaryContainer = NeutralGray200,
                onSecondaryContainer = NeutralGray900,
                tertiary = TealAccent,
                onTertiary = NeutralWhite,
                background = NeutralGray100,
                onBackground = NeutralGray900,
                surface = NeutralWhite,
                onSurface = NeutralGray900,
                surfaceVariant = NeutralGray100,
                onSurfaceVariant = NeutralGray600,
                outline = NeutralGray400,
                outlineVariant = NeutralGray200,
                error = ErrorRed,
                onError = NeutralWhite,
                errorContainer = Color(0xFFFCE4E4),
                onErrorContainer = Color(0xFF8B0000),
        )

private val LalamoveDarkColorScheme =
        darkColorScheme(
                primary = LalamoveOrangeLight,
                onPrimary = NeutralDark,
                primaryContainer = LalamoveOrangeDark,
                onPrimaryContainer = LalamoveOrangeSurface,
                secondary = NeutralGray400,
                onSecondary = NeutralDark,
                secondaryContainer = NeutralGray800,
                onSecondaryContainer = NeutralGray200,
                tertiary = TealAccent,
                onTertiary = NeutralDark,
                background = NeutralDark,
                onBackground = NeutralGray200,
                surface = NeutralGray900,
                onSurface = NeutralGray200,
                surfaceVariant = NeutralGray800,
                onSurfaceVariant = NeutralGray400,
                outline = NeutralGray600,
                outlineVariant = NeutralGray800,
                error = Color(0xFFFF6B6B),
                onError = NeutralDark,
        )

@Composable
fun LalamoveTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) LalamoveDarkColorScheme else LalamoveLightColorScheme

    MaterialTheme(
            colorScheme = colorScheme,
            typography = LalamoveTypography,
            shapes = LalamoveShapes,
            content = content
    )
}
