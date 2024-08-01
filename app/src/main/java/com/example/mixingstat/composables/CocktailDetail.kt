package com.example.mixingstat.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mixingstat.config.Screen
import com.example.mixingstat.models.Cocktail

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CocktailDetail(cocktail: Cocktail, navigateTo: (route: String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = cocktail.strDrink, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        AsyncImage(
            model = cocktail.strDrinkThumb,
            contentDescription = cocktail.strDrink,
            modifier = Modifier.height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Instructions: ${cocktail.strInstructions}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ingredients:")
        Spacer(modifier = Modifier.height(8.dp))
        val ingredients = listOf(
            cocktail.strIngredient1,
            cocktail.strIngredient2,
            cocktail.strIngredient3,
            cocktail.strIngredient4,
            cocktail.strIngredient5,
            cocktail.strIngredient6,
            cocktail.strIngredient7,
            cocktail.strIngredient8,
            cocktail.strIngredient9,
            cocktail.strIngredient10,
            cocktail.strIngredient11,
            cocktail.strIngredient12,
            cocktail.strIngredient13,
            cocktail.strIngredient14,
            cocktail.strIngredient15
        )
        FlowRow(modifier = Modifier.padding(1.dp)) {
            for (ingredient in ingredients) {
                if (!ingredient.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.width(1.dp))
                    Button(onClick = { navigateTo("${Screen.Search.route}/${ingredient}") }) {
                        Text(text = ingredient)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Measures: ${cocktail.strMeasure1}, ${cocktail.strMeasure2}, ${cocktail.strMeasure3}, ${cocktail.strMeasure4}, ${cocktail.strMeasure5}, ${cocktail.strMeasure6}, ${cocktail.strMeasure7}, ${cocktail.strMeasure8}, ${cocktail.strMeasure9}, ${cocktail.strMeasure10}, ${cocktail.strMeasure11}, ${cocktail.strMeasure12}, ${cocktail.strMeasure13}, ${cocktail.strMeasure14}, ${cocktail.strMeasure15}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Category: ${cocktail.strCategory}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Glass: ${cocktail.strGlass}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Alcoholic: ${cocktail.strAlcoholic}")
    }
}