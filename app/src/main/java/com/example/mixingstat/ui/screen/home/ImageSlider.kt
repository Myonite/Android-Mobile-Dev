package com.example.mixingstat.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mixingstat.data.models.Cocktail

@Composable
fun ImageSlider(cocktails: List<Cocktail>, navigateTo: (route: String) -> Unit, modifier: Modifier = Modifier, text: String? = null) {
    Column {
        text?.let {
            Text(
                it,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        LazyRow(modifier = modifier) {
            itemsIndexed(cocktails) { _, cocktail ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        navigateTo("cocktail/${cocktail.idDrink}")
                    }
                ) {
                    Card(
                        modifier = Modifier.padding(8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = cocktail.strDrinkThumb,
                                contentDescription = cocktail.strDrink
                            )
                        }
                    }
                }
            }
        }
    }
}