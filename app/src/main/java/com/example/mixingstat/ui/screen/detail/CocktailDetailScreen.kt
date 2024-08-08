package com.example.mixingstat.ui.screen.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mixingstat.R
import com.example.mixingstat.presentation.viewmodel.CocktailDetailViewModel

/**
 * Composable function that displays the CocktailDetail screen.
 *
 * @param id The ID of the cocktail to display.
 * @param navigateTo A function to navigate to different screens.
 * @param viewModel The ViewModel associated with this screen.
 */
@Composable
fun CocktailDetailScreen(
    id: String, navigateTo: (route: String) -> Unit,
    viewModel: CocktailDetailViewModel = hiltViewModel()
) {
    // Collect the state from the ViewModel
    val state by viewModel.state.collectAsState()

    // Launch an effect to search for the cocktail by its ID
    LaunchedEffect(id) {
        viewModel.searchById(id)
    }

    // If the cocktail is not null, display its details, otherwise display an error message
    if (state.cocktail != null) {
        CocktailDetail(state.cocktail!!, navigateTo)
    } else {
        Text(
            text = stringResource(R.string.error_cocktail_not_found),
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}