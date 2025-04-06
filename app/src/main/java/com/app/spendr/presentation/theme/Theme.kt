package com.app.spendr.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val colorSchemeDark = ColorScheme(
    primary = Color(0xFFE9E9E9),
    primaryContainer = Color(0xFF1E1E1E),
    secondary = Color(0xFF292D32),
    secondaryContainer = Color(0xFFE9E9E9),
    tertiary = Color(0xFFF3F3F3),
    tertiaryContainer = Color(0xFF353535),
    inversePrimary = Color(0x50B6ADAD),

    onPrimary = Color(0xFF1E1E1E),
    onPrimaryContainer = Color(0xFFE9E9E9),
    onSecondary = Color(0xFFE9E9E9),
    onSecondaryContainer = Color(0xFF292D32),
    onTertiary = Color(0xFF3A3939),
    onTertiaryContainer = Color(0xFFF3F3F3),
    background = Color(0xFF1E1E1E),
    onBackground = Color(0xFFE9E9E9),
    error = Color.Red,
    onError = Color(0xFFE9E9E9),
    onErrorContainer = Color.Transparent,
    surface = Color.Unspecified,
    onSurface = Color.Unspecified,
    surfaceVariant = Color.Unspecified,
    onSurfaceVariant = Color.Unspecified,
    scrim = Color(0xFF4B4B4B),
    surfaceTint = Color.Unspecified,
    inverseSurface = Color.Unspecified,
    inverseOnSurface = Color.Unspecified,
    errorContainer = Color.Unspecified,
    outlineVariant = Color.Unspecified,
    outline = Color.Unspecified
    )

val colorSchemeLight = ColorScheme(
    primary = Color(0xFF1E1E1E),
    primaryContainer = Color(0xFFE9E9E9),
    secondary = Color(0xFFE9E9E9),
    secondaryContainer = Color(0xFF292D32),
    tertiary = Color(0xFF353535),
    tertiaryContainer = Color(0xFFC4C4C4),
    inversePrimary = Color(0xFFE9E9E9),

    onPrimary = Color(0xFFE9E9E9),
    onPrimaryContainer = Color(0xFF1E1E1E),
    onSecondary = Color(0xFF292D32),
    onSecondaryContainer = Color(0xFFE9E9E9),
    onTertiary = Color(0xFFF3F3F3),
    onTertiaryContainer = Color(0xFF3A3939),
    background = Color(0xFFE9E9E9),
    onBackground = Color(0xFF1E1E1E),
    error = Color.Red,
    onError = Color(0xFFE9E9E9),
    onErrorContainer = Color.Transparent,
    surface = Color.Unspecified,
    onSurface = Color.Unspecified,
    surfaceVariant = Color.Unspecified,
    onSurfaceVariant = Color.Unspecified,
    scrim = Color(0xFFA3A3A3),
    surfaceTint = Color.Unspecified,
    inverseSurface = Color.Unspecified,
    inverseOnSurface = Color.Unspecified,
    errorContainer = Color.Unspecified,
    outlineVariant = Color.Unspecified,
    outline = Color.Unspecified
)


@Composable
fun SpendrTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        colorSchemeDark

    } else {
        colorSchemeDark
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}