package com.example.mixingstat.ui.screen.detail


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mixingstat.data.models.Cocktail

@Composable
fun CocktailDetail(cocktail: Cocktail, navigateTo: (route: String) -> Unit) {
    val nonNullIngredients =
        cocktail.getIngredientsWithMeasures(cocktail).filter { !it.first.isNullOrEmpty() }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CocktailHeader(cocktail)
        InstructionsSection(cocktail)
        IngredientsSection(nonNullIngredients, navigateTo)
        DetailsSection(cocktail)
    }
}
