package com.example.mixingstat.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mixingstat.data.models.Cocktail

/**
 * Composable function that displays the details of a cocktail.
 *
 * @param cocktail The cocktail whose details are to be displayed.
 * @param navigateTo A function to navigate to different screens.
 */
@Composable
fun CocktailDetail(cocktail: Cocktail, navigateTo: (route: String) -> Unit) {
    // Filter out any null or empty ingredients from the cocktail's ingredients list
    val nonNullIngredients =
        cocktail.getIngredientsWithMeasures(cocktail).filter { !it.first.isNullOrEmpty() }

    Column(
        modifier = Modifier
            .padding(16.dp) // Apply padding of 16dp
            .verticalScroll(rememberScrollState()) // Enable vertical scrolling
    ) {
        // Display the header, instructions, ingredients, and details of the cocktail
        CocktailHeader(cocktail)
        InstructionsSection(cocktail)
        IngredientsSection(nonNullIngredients, navigateTo)
        DetailsSection(cocktail)
    }
}