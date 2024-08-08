package com.example.mixingstat.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mixingstat.config.Screen

/**
 * Composable function that displays a row for an ingredient.
 *
 * This function creates a Row layout with an image of the ingredient, the name and measure of the ingredient, and a search icon.
 * The row is clickable and navigates to the Search screen when clicked.
 *
 * @param ingredient The name of the ingredient.
 * @param measure The measure of the ingredient.
 * @param navigateTo A function to navigate to different screens.
 */
@Composable
fun IngredientRow(ingredient: String, measure: String, navigateTo: (route: String) -> Unit) {
    // Replace spaces in the ingredient name with "%20" for URL encoding
    val ingredientEncoded = ingredient.replace(" ", "%20")
    // Trim any extra spaces from the measure
    val measureTrimmed = measure.trim()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateTo("${Screen.Search.route}/$ingredient/ingredient") }
    ) {
        // Display an image of the ingredient
        AsyncImage(
            model = "https://www.thecocktaildb.com/images/ingredients/${ingredientEncoded}-Small.png",
            contentDescription = ingredient,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
        )

        // Display the name and measure of the ingredient
        Text(text = "$ingredient ($measureTrimmed)", modifier = Modifier.padding(start = 18.dp))

        // Add a spacer to push the search icon to the end of the row
        Spacer(modifier = Modifier.weight(1f))

        // Display a search icon
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    }
}