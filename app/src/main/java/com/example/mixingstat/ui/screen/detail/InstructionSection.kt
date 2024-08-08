package com.example.mixingstat.ui.screen.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mixingstat.R
import com.example.mixingstat.data.models.Cocktail

/**
 * Composable function that displays the instructions for a cocktail.
 *
 * This function creates a Text component for the title and another Text component for the instructions of the cocktail.
 * The title and instructions are styled according to the MaterialTheme.
 *
 * @param cocktail The cocktail whose instructions are to be displayed.
 */
@Composable
fun InstructionsSection(cocktail: Cocktail) {
    // Display the title for the instructions section
    Text(
        text = stringResource(R.string.instructions),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(top = 16.dp)
    )
    // Display the instructions for the cocktail
    Text(
        text = "${cocktail.strInstructions}",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(top = 10.dp)
    )
}