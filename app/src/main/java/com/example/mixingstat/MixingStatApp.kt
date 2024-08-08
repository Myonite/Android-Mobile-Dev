package com.example.mixingstat

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mixingstat.presentation.navigation.NavGraph

/**
 * Composable function that represents the main application.
 *
 * This function sets up the main structure of the application, including the top bar, bottom navigation bar, and content.
 * It also sets up a SnackbarHost for displaying snackbars.
 *
 * @Preview annotation is used to render the composable in the preview pane of the IDE.
 */
@Preview(widthDp = 50, heightDp = 50)
@Composable
fun MixingStatApp() {
    // Remember a NavController, which maintains the navigation stack.
    val navController = rememberNavController()
    // Remember a SnackbarHostState, which maintains the state of the SnackbarHost.
    val snackbarHostState = remember { SnackbarHostState() }

    // Scaffold is a composable that implements the basic material design visual layout structure.
    Scaffold(
        // TopBar is a composable function that displays the top bar.
        topBar = {
            TopBar()
        },
        // BottomNavigationBar is a composable function that displays the bottom navigation bar.
        bottomBar = {
            BottomNavigationBar(navController)
        },
        // SnackbarHost is a composable that can display snackbars.
        snackbarHost = { SnackbarHost(snackbarHostState) },
        // The content of the Scaffold is the NavGraph, which controls the navigation between different screens.
        content = {
            NavGraph(navController, it)
        })
}