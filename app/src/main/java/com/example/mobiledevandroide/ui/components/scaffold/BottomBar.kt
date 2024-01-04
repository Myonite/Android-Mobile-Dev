package com.example.mobiledevandroide.ui.components.scaffold

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobiledevandroide.utils.Direction
import com.example.mobiledevandroide.viewModels.AuthViewModel

@Composable
fun BottomBar(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(),
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth(),
    ) {
        IconButton(
            onClick = {
                if (navController.currentBackStackEntry?.destination?.route != Direction.Home.route) {
                    navController.navigate(Direction.Home.route)
                }
            },
            modifier = Modifier.weight(1f).testTag("Home")
        ) {
            Icon(Direction.Home.icon, contentDescription = null)
        }

        IconButton(
            onClick = {
                      navController.navigate(Direction.About.route)
            },
            modifier = Modifier.weight(1f).testTag("About")
        ) {
            Icon(Direction.About.icon, contentDescription = null)
        }

        IconButton(
            onClick = {
                authViewModel.logout {
                    navController.navigate(Direction.Login.route)
                }
            },
            modifier = Modifier.weight(1f).testTag("Logout")
        ) {
            Icon(Direction.Logout.icon, contentDescription = null)
        }
    }
}
