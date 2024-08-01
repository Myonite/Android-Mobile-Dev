package com.example.mixingstat.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mixingstat.config.Screen
import com.example.mixingstat.ui.common.CocktailDetailScreen
import com.example.mixingstat.ui.screen.home.HomeScreen
import com.example.mixingstat.ui.screen.random.RandomDrinkScreen
import com.example.mixingstat.ui.screen.search.SearchScreen

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    Box(modifier = androidx.compose.ui.Modifier.padding(paddingValues)) {
        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                HomeScreen(navigateTo = {
                    navController.navigate(it)
                })
            }
            composable(Screen.Random.route) {
                RandomDrinkScreen(navigateTo = {
                    navController.navigate(it)
                })
            }
            composable(Screen.Search.route) { SearchScreen() }
            composable("${Screen.Search.route}/{searchQuery}") { backStackEntry ->
                val searchQuery = backStackEntry.arguments?.getString("searchQuery")
                SearchScreen(searchQuery)
            }
            composable("cocktail/{cocktailId}") { backStackEntry ->
                CocktailDetailScreen(
                    backStackEntry.arguments?.getString("cocktailId")!!,
                    navigateTo = { navController.navigate(it) })
            }
        }
    }

}