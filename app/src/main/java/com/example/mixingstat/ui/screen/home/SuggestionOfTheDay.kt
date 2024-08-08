package com.example.mixingstat.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mixingstat.R
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.utils.StringUtils

/**
 * Composable function that displays a suggestion of the day.
 *
 * This function creates a Card layout with a clickable modifier that navigates to the detail screen of the suggested cocktail when clicked.
 * The Card contains a Row layout with an image of the cocktail and a Column layout with the name, category, alcoholic content, and glass type of the cocktail.
 * The title of the suggestion is displayed above the Card.
 *
 * @param cocktail The cocktail to be suggested.
 * @param navigateTo A function that takes a route as a parameter and navigates to the corresponding screen.
 */
@Composable
fun SuggestionOfTheDay(cocktail: Cocktail, navigateTo: (route: String) -> Unit) {
    // Display the title for the suggestion of the day
    Text(
        text = stringResource(R.string.title_suggestion_of_the_moment),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    // Create a Card layout for the suggested cocktail
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navigateTo("cocktail/${cocktail.idDrink}") }.semantics { testTag = "SuggestionCard${cocktail.idDrink}" },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        // Create a Row layout for the image and details of the cocktail
        Row(
            modifier = Modifier
        ) {
            // Display an image of the cocktail
            AsyncImage(
                model = cocktail.strDrinkThumb,
                contentDescription = cocktail.strDrink,
                modifier = Modifier
                    .height(160.dp)
                    .width(160.dp)
            )
            // Create a Column layout for the details of the cocktail
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                // Display the name of the cocktail
                Text(
                    text = cocktail.strDrink,
                    fontSize = 30.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                // Display the category of the cocktail
                Text(
                    text = stringResource(R.string.category) + ": " + StringUtils.defaultIfNull(
                        cocktail.strCategory
                    )
                )
                // Display whether the cocktail is alcoholic or not
                Text(
                    text = stringResource(R.string.alcoholic) + ": " + StringUtils.defaultIfNull(
                        cocktail.strAlcoholic
                    )
                )
                // Display the type of glass used for the cocktail
                Text(
                    text = stringResource(R.string.glass) + ": " + StringUtils.defaultIfNull(
                        cocktail.strGlass
                    )
                )
            }
        }
    }
}