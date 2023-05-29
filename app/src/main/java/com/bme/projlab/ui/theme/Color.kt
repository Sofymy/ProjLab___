package com.bme.projlab.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF7adbff)
val Purple = Color(0xFF7c62c9)
val White = Color(0xFFFFFFFF)
val Grey = Color(0xFFC4C4C4)
val Text = Color(0xFF3C3C3C)
val Background = Color(0xFFF5F3F0)
val GradientBrush = Brush.verticalGradient(
    colors = listOf(
        Blue,
        Purple
    )
)
val TransparentBrush = Brush.verticalGradient(
    colors = listOf(
        Color.Transparent,
        Background,
        Background,
        Background
    ),
)