package com.example.mixingstat.config

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mixingstat.R

sealed class Screen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {
    data object Home : Screen("home_route", Icons.Filled.Home, R.string.title_home)
    data object Random : Screen("random_drink_route",Icons.Filled.QuestionMark, R.string.title_random)
    data object Search : Screen("search_route",Icons.Filled
        .Search, R.string.title_search
    )
}

val botNavScreenList = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Random
)

