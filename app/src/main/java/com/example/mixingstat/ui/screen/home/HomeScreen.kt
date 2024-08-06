package com.example.mixingstat.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mixingstat.R
import com.example.mixingstat.presentation.viewmodel.home.HomeScreenViewModel

/**
 * HomeScreen is a Composable function that displays the home screen of the application.
 *
 * @param navigateTo A function that takes a route as a parameter and navigates to the corresponding screen.
 * @param viewModel The ViewModel that provides the state for the HomeScreen. By default, it uses the HomeScreenViewModel provided by Hilt.
 */
@Composable
fun HomeScreen(
    navigateTo: (route: String) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.semantics { testTag = "LoadingIndicator" })
        }
    } else {
        if (state.popularCocktails?.isEmpty() == true && state.latestCocktails?.isEmpty() == true && state.suggestionCocktail == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    stringResource(R.string.possible_network_error),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().semantics { contentDescription = "Error message" }
                )
            }
        } else {
            Row(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    state.popularCocktails?.let { cocktails ->
                        ImageSlider(
                            cocktails,
                            navigateTo,
                            text = stringResource(R.string.title_popular_cocktails)
                        )
                    }
                    state.latestCocktails?.let { cocktails ->
                        ImageSlider(
                            cocktails,
                            navigateTo,
                            text = stringResource(R.string.title_latest_cocktails)
                        )
                    }
                    state.suggestionCocktail?.let { SuggestionOfTheDay(it, navigateTo) }
                }
            }
        }
    }
}