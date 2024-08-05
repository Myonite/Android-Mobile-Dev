package com.example.mixingstat.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mixingstat.R
import com.example.mixingstat.presentation.viewmodel.home.HomeScreenViewModel

@Composable
fun HomeScreen(
    navigateTo: (route: String) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        if (state.popularCocktails.isEmpty() && state.latestCocktails.isEmpty() && state.suggestionCocktail == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    stringResource(R.string.possible_network_error),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            Row(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ImageSlider(
                        state.popularCocktails,
                        navigateTo,
                        text = stringResource(R.string.title_popular_cocktails)
                    )
                    ImageSlider(
                        state.latestCocktails,
                        navigateTo,
                        text = stringResource(R.string.title_latest_cocktails)
                    )
                    state.suggestionCocktail?.let { SuggestionOfTheDay(it, navigateTo) }
                }
            }
        }
    }
}