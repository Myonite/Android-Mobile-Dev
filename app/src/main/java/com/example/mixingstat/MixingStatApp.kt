package com.example.mixingstat

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mixingstat.presentation.navigation.NavGraph

@Preview(widthDp = 50, heightDp = 50)
@Composable
fun MixingStatApp() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(

        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },

        content = {
            NavGraph(navController, it)
        })
}

