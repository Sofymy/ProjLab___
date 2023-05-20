package com.bme.projlab.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bme.projlab.R

val fonts = FontFamily(
    Font(R.font.lato_regular),
    Font(R.font.lato_italic, style = FontStyle.Italic),
    Font(R.font.lato_black, weight = FontWeight.Black),
    Font(R.font.lato_bold, weight = FontWeight.Bold),
    Font(R.font.lato_light, weight = FontWeight.Light),
    Font(R.font.lato_thin, weight = FontWeight.Thin)
)

val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    )
)