package com.example.mixingstat.config

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Composable
fun MixingStatTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    val svgColor = if (darkTheme) Color.White else Color.Black

    MaterialTheme(
        colorScheme = colors,
        content = { ContentWithSvgColor(svgColor, content) }
    )
}


private val DarkColors = darkColorScheme(
    primary = Color(0xFF86BBFC), secondary = Color(0xFF03DAC6)
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF6200EE), secondary = Color(0xFF03DAC6)
)

@Composable
fun ContentWithSvgColor(svgColor: Color, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSvgColor provides svgColor) {
        content()
    }
}


val LocalSvgColor = staticCompositionLocalOf { Color.Unspecified }