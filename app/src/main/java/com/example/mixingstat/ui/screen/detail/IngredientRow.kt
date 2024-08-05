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

@Composable
fun IngredientRow(ingredient: String, measure: String, navigateTo: (route: String) -> Unit) {
    val ingredientEncoded = ingredient.replace(" ", "%20")
    val measureTrimmed =
        measure.trim() //Some entries contained weird spacing that wasn't trimmed by the backend
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateTo("${Screen.Search.route}/$ingredient/ingredient") }
    ) {
        AsyncImage(
            model = "https://www.thecocktaildb.com/images/ingredients/${ingredientEncoded}-Small.png",
            contentDescription = ingredient,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
        )

        Text(text = "$ingredient ($measureTrimmed)", modifier = Modifier.padding(start = 18.dp))
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    }
}