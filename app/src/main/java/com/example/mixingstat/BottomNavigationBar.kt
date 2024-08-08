package com.example.mixingstat

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mixingstat.config.botNavScreenList

/**
 * Composable function that displays a bottom navigation bar.
 *
 * This function displays a bottom navigation bar with items for each screen in the botNavScreenList.
 * Each item has an icon and a label, and is selected if its route matches the current destination's route.
 * When an item is clicked, the function navigates to the corresponding screen and saves the state.
 *
 * @param navController The NavController that controls the navigation between screens.
 */
@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        botNavScreenList.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = screen.resourceId.toString()
                    )
                },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}