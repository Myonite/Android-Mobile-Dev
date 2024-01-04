package com.example.mobiledevandroide

import DetailsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobiledevandroide.store.JwtManager
import com.example.mobiledevandroide.store.SharedPreferencesManager
import com.example.mobiledevandroide.ui.theme.MobileDevAndroideTheme
import com.example.mobiledevandroide.ui.views.AboutScreen
import com.example.mobiledevandroide.ui.views.LoginScreen
import com.example.mobiledevandroide.ui.views.MainScreen
import com.example.mobiledevandroide.utils.Direction

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobileDevAndroideTheme {
                Surface {
                    ReceiptApp()
                }
            }
        }
    }
}

@Composable
fun ReceiptApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination =
        if (JwtManager.getInstance(
                SharedPreferencesManager
                    .getInstance(context),
            ).hasValidJwtToken()
        ) {
            Direction.Home.route
        } else {
            Direction.Login.route
        },
    ) {
        composable(Direction.Login.route) {
            LoginScreen {
                navController.navigate(it)
            }
        }

        composable(Direction.Home.route) {
            MainScreen(navController) {
                navController.navigate(it)
            }
        }
        composable(Direction.About.route) {
            AboutScreen(navController)
        }

        composable(
            "details/{receiptId}",
            arguments = listOf(
                navArgument("receiptId") {
                    type = NavType.StringType
                },
            ),
        ) { backStackEntry ->
            val receiptId = backStackEntry.arguments?.getString("receiptId")
            if (receiptId != null) {
                DetailsScreen(receiptId = receiptId, navController)
            }
        }
    }
}
