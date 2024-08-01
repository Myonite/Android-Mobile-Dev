package com.example.mixingstat.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.mixingstat.R
import com.example.mixingstat.getCocktailById

@Composable
fun CocktailDetailScreen(cocktailId: String, navigateTo: (route: String) -> Unit) {
    val cocktail = getCocktailById(cocktailId)
    if (cocktail != null) {
        CocktailDetail(cocktail, navigateTo)
    } else {
        Text(
            text = stringResource(R.string.error_cocktail_not_found),
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}