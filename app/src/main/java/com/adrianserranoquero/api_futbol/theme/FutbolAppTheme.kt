package com.adrianserranoquero.api_futbol.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
private val LightThemeColors = lightColors(
    primary = androidx.compose.ui.graphics.Color(0xFF1976D2),
    primaryVariant = androidx.compose.ui.graphics.Color(0xFF1565C0),
    secondary = androidx.compose.ui.graphics.Color(0xFFFFC107)
)
private val DarkThemeColors = darkColors(
    primary = androidx.compose.ui.graphics.Color(0xFFBB86FC),
    primaryVariant = androidx.compose.ui.graphics.Color(0xFF3700B3),
    secondary = androidx.compose.ui.graphics.Color(0xFF03DAC5)
)
@Composable
fun FutbolAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightThemeColors,
        content = content
    )
}