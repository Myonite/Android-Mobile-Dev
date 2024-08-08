package com.example.mixingstat.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Composable function that displays a random drink screen.
 *
 * This function displays a question mark icon that rotates back and forth while the random drink is being loaded.
 * Once the random drink is loaded, it displays the details of the drink using the CocktailDetailScreen composable.
 * If the random drink cannot be loaded, it displays an error message.
 *
 * @param navigateTo A function that takes a route as a parameter and navigates to the corresponding screen.
 * @param viewModel The ViewModel that provides the state for the RandomDrinkScreen. By default, it uses the RandomDrinkViewModel provided by Hilt.
 */
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
    primary = Color(0xFFFF5722), secondary = Color(0xFFF44336)
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF307392), secondary = Color(0xFF0FAC9D)
)

/**
 * Composable function that applies a given SVG color to its content.
 *
 * This function provides the given SVG color to its content through the LocalSvgColor composition local.
 *
 * @param svgColor The SVG color to be applied to the content.
 * @param content The content to which the SVG color should be applied.
 */
@Composable
fun ContentWithSvgColor(svgColor: Color, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSvgColor provides svgColor) {
        content()
    }
}


val LocalSvgColor = staticCompositionLocalOf { Color.Unspecified }