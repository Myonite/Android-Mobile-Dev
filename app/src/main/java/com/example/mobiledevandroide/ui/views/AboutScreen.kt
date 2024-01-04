package com.example.mobiledevandroide.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobiledevandroide.ui.components.scaffold.BottomBar
import com.example.mobiledevandroide.ui.components.scaffold.TopBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = { BottomBar(navController) },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "MobileDevAndroid",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "Version 1.0",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                Text(
                    text = "An application based on a project for webservices, some parts have been reduced to allow for a more fluent use on a mobile device.",
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("https://homeadmin-back-0d52c04e5b09.herokuapp.com/swagger",style=MaterialTheme.typography.bodySmall)
            }


        }
    }
}





