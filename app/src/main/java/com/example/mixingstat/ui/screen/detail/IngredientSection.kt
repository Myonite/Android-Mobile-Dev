package com.example.mixingstat.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mixingstat.R

/**
 * Composable function that displays a list of ingredients for a cocktail.
 *
 * This function creates a Column layout and displays each ingredient in a row using the IngredientRow composable.
 * Each row displays the name and measure of the ingredient.
 * A horizontal divider is added between each ingredient row.
 *
 * @param nonNullIngredients A list of pairs of ingredient names and measures. Each pair represents an ingredient and its measure.
 * @param navigateTo A function to navigate to different screens.
 */
@Composable
fun IngredientsSection(
    nonNullIngredients: List<Pair<String?, String?>>,
    navigateTo: (route: String) -> Unit
) {
    // Display the title for the ingredients section
    Text(
        text = stringResource(R.string.ingredients),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    )
    // Create a Column layout for the ingredients
    Column(modifier = Modifier.padding(1.dp)) {
        // Loop through each ingredient and measure
        for ((index, ingredientMeasure) in nonNullIngredients.withIndex()) {
            val (ingredient, measure) = ingredientMeasure
            // Display each ingredient and measure in a row
            IngredientRow(
                ingredient = ingredient ?: stringResource(R.string.ingredient_not_found),
                measure = measure ?: stringResource(R.string.measurement_not_found),
                navigateTo = navigateTo
            )
            // Add a horizontal divider between each ingredient row, except for the last one
            if (index < nonNullIngredients.size - 1) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}