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


@Composable
fun IngredientsSection(
    nonNullIngredients: List<Pair<String?, String?>>,
    navigateTo: (route: String) -> Unit
) {
    Text(
        text = stringResource(R.string.ingredients),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    )
    Column(modifier = Modifier.padding(1.dp)) {
        for ((index, ingredientMeasure) in nonNullIngredients.withIndex()) {
            val (ingredient, measure) = ingredientMeasure
            IngredientRow(
                ingredient = ingredient ?: stringResource(R.string.ingredient_not_found),
                measure = measure ?: stringResource(R.string.measurement_not_found),
                navigateTo = navigateTo
            )
            if (index < nonNullIngredients.size - 1) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}