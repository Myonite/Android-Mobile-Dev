package com.example.mobiledevandroide.ui.components.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mobiledevandroide.utils.Direction

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(navController: NavController) {
    val previousBackStackEntry = navController.previousBackStackEntry

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("HomeAdministration")
        },
        navigationIcon = {
            previousBackStackEntry?.destination?.takeIf { it.route != Direction.Login.route }?.let {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        },

    )
}
