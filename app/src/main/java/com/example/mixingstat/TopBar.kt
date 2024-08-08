package com.example.mixingstat

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow

/**
 * Composable function that represents the top bar of the application.
 *
 * This function displays a top bar with the app name as the title. The title is centered and its text overflows with an ellipsis if it's too long.
 * The color of the top bar is determined by the primaryContainer color of the current MaterialTheme's color scheme.
 *
 * @OptIn annotation is used to opt-in to using the ExperimentalMaterial3Api.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        // The title of the top bar is the app name.
        title = {
            Text(stringResource(R.string.app_name), maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        // The color of the top bar is determined by the primaryContainer color of the current MaterialTheme's color scheme.
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
    )
}