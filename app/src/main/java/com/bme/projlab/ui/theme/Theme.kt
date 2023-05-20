package com.bme.projlab.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = Blue,
    secondary = Orange,
    background = Background,
    surface = Blue,
    primaryContainer = Blue
)

private val LightColorPalette = lightColorScheme(
    primary = Blue,
    secondary = Orange,
    background = Background,
    surface = Blue,
    primaryContainer = Blue
)

@Composable
fun AppppppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}