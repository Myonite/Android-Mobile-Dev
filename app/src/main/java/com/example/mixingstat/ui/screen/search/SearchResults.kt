package com.example.mixingstat.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mixingstat.data.models.Cocktail

/**
 * Composable function that displays a random drink screen.
 *
 * This function displays a question mark icon that rotates back and forth while the random drink is being loaded.
 * Once the random drink is loaded, it displays the details of the drink using the CocktailDetailScreen composable.
 * If the random drink cannot be loaded, it displays an error message.
 *
 * @param navigateTo A function that takes a route as a parameter and navigates to the corresponding screen.
 * @param viewModel The ViewModel that provides the state for the RandomDrinkScreen. By default, it uses the RandomDrinkViewModel provided by Hilt.
 */
@Composable
fun SearchResults(searchResults: List<Cocktail>?, navigateTo: (route: String) -> Unit) {
    if (searchResults.isNullOrEmpty()) {
        Text(
            text = "No results",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp)
        )
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(top = 70.dp)
        ) {
            items(searchResults) { cocktail ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { navigateTo("cocktail/${cocktail.idDrink}") }) {
                    Column {
                        AsyncImage(
                            model = cocktail.strDrinkThumb,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                        Text(
                            text = cocktail.strDrink,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}