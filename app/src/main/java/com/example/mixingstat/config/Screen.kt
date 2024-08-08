package com.example.mixingstat.config

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mixingstat.R

/**
 * A sealed class representing the different screens in the application.
 * Each screen has a route, an icon, and a resource ID for its title.
 */
sealed class Screen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {
    /**
     * Represents the Home screen.
     */
    data object Home : Screen("home_route", Icons.Filled.Home, R.string.title_home)

    /**
     * Represents the Random screen.
     */
    data object Random : Screen("random_drink_route",Icons.Filled.QuestionMark, R.string.title_random)

    /**
     * Represents the Search screen.
     */
    data object Search : Screen("search_route",Icons.Filled.Search, R.string.title_search)
}

/**
 * A list of all the bottom navigation screens in the application.
 */
val botNavScreenList = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Random
)