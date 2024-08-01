package com.example.mixingstat.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mixingstat.R
import com.example.mixingstat.dev_seeding.cokatilList
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