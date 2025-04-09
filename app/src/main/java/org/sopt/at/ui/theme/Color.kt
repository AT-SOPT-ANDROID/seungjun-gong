package org.sopt.at.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

@Immutable
data class BasicColors(
    val gray100: Color = Color(0xFFF5F5F5),
    val gray200: Color = Color(0xFFEEEEEE),
    val gray300: Color = Color(0xFFE0E0E0),
    val gray400: Color = Color(0xFFBDBDBD),
    val gray500: Color = Color(0xFF9E9E9E),
    val gray600: Color = Color(0xFF616161),
    val gray700: Color = Color(0xFF616161),
    val gray800: Color = Color(0xFF424242),
    val gray900: Color = Color(0xFF212121),

    val redA400: Color = Color(0xFFFF1744),
)

val basicColors = BasicColors()