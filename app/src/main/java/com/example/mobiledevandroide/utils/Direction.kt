package com.example.mobiledevandroide.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Direction(val route: String, val icon: ImageVector) {
    object Login : Direction("login", Icons.Default.Lock)
    object Home : Direction("home", Icons.Default.Home)
    object About : Direction("about", Icons.Default.Info)
    object Logout : Direction("logout", Icons.Default.Logout)
}
