package com.example.mixingstat.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mixingstat.R
import com.example.mixingstat.data.models.Cocktail

/**
 * Composable function that displays a list of cocktails in a horizontal slider.
 *
 * This function creates a LazyRow layout and displays each cocktail in a Card.
 * Each Card contains an image of the cocktail and is clickable, navigating to the detail screen of the cocktail when clicked.
 * If the list of cocktails is empty, it displays an error message.
 *
 * @param cocktails The list of cocktails to be displayed in the slider.
 * @param navigateTo A function that takes a route as a parameter and navigates to the corresponding screen.
 * @param modifier The modifier to be applied to the LazyRow layout.
 * @param text The title to be displayed above the slider. If null, no title is displayed.
 */
@Composable
fun ImageSlider(
    cocktails: List<Cocktail>,
    navigateTo: (route: String) -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null
) {
    if (cocktails.isEmpty()) {
        Text(
            stringResource(R.string.possible_network_error),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().semantics { testTag = "ErrorMessage" }
        )
    } else {
        Column {
            text?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            LazyRow(modifier = modifier) {
                itemsIndexed(cocktails) { _, cocktail ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            navigateTo("cocktail/${cocktail.idDrink}")
                        }
                    ) {
                        Card(
                            modifier = Modifier.padding(8.dp).semantics { testTag = "CocktailCard${cocktail.idDrink}" },
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                            ) {
                                AsyncImage(
                                    model = cocktail.strDrinkThumb,
                                    contentDescription = cocktail.strDrink
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}