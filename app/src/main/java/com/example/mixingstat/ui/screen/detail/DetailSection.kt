package com.example.mixingstat.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
 * Composable function that displays the details of a cocktail.
 *
 * This function creates a Column layout and displays the category, glass type, and alcoholic content of the cocktail.
 * Each detail is displayed in a Row layout with the label on the left and the value on the right.
 *
 * @param cocktail The cocktail whose details are to be displayed.
 */
@Composable
fun DetailsSection(cocktail: Cocktail) {
    Column(modifier = Modifier.padding(top = 20.dp)) {
        listOf(
            Pair(stringResource(R.string.category), cocktail.strCategory),
            Pair(stringResource(R.string.glass), cocktail.strGlass),
            Pair(stringResource(R.string.alcoholic), cocktail.strAlcoholic)
        ).forEach { (label, value) ->
            Row {
                Text(
                    text = label,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = value ?: "",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}