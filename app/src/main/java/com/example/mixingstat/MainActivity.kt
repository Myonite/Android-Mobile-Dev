package com.example.mixingstat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mixingstat.composables.CocktailScreen
import com.example.mixingstat.config.MixingStatTheme
import com.example.mixingstat.config.Screen
import com.example.mixingstat.config.botNavScreenList
import com.example.mixingstat.dev_seeding.cocktail
import com.example.mixingstat.models.Cocktail
import com.example.mixingstat.screen.HomeScreen
import com.example.mixingstat.screen.RandomDrinkScreen
import com.example.mixingstat.screen.SearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MixingStatTheme {
                Surface {
                    MixingStatApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 50, heightDp = 50)
@Composable
fun MixingStatApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Mixing Stat", maxLines = 1, overflow = TextOverflow.Ellipsis)
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
            )
        },
        bottomBar = {
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
        },


        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavHost(
                    navController = navController, startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) { HomeScreen { navController.navigate(it) } }
                    composable(Screen.Random.route) { RandomDrinkScreen { navController.navigate(it) } }
                    composable(Screen.Search.route) { SearchScreen() }
                    composable("${Screen.Search.route}/{searchQuery}") { backStackEntry ->
                        val searchQuery = backStackEntry.arguments?.getString("searchQuery")
                        SearchScreen(searchQuery)
                    }
                    composable("cocktail/{cocktailId}") { backStackEntry ->
                        CocktailScreen(backStackEntry.arguments?.getString("cocktailId")!!) { route ->
                            navController.navigate(route)
                        }
                    }

                }
            }
        })
}


fun getCocktailById(id: String): Cocktail? {
    return cocktail.find { it.idDrink == id }
}










